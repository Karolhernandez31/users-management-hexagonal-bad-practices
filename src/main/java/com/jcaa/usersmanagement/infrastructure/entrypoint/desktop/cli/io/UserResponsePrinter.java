package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import java.util.List;
import java.util.Map;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-10s : %s%n";
  private static final Map<String, String> STATUS_LABELS = Map.of(
      "ACTIVE", "Activo",
      "INACTIVE", "Inactivo",
      "PENDING", "Pendiente de activacion",
      "BLOCKED", "Bloqueado",
      "DELETED", "Eliminado");

  private final ConsoleIO console;

  public void print(final UserResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",     response.id());
    console.printf(ROW_FORMAT, "Name",   response.name());
    console.printf(ROW_FORMAT, "Email",  response.email());
    console.printf(ROW_FORMAT, "Role",   response.role());
    // Clean Code - Regla 16: se llama al auxiliar que tiene la cadena if/else larga
    console.printf(ROW_FORMAT, "Status", getStatusLabel(response.status()));
    console.println(SEPARATOR);
  }

  public void printList(final List<UserResponse> users) {
    if (users == null || users.isEmpty()) {
      console.println("  No users found.");
      return;
    }

    console.printf("%n  Total: %d user(s)%n", users.size());
    users.forEach(this::print);
  }

  // Clean Code - Regla 27 (código listo para leer, no solo para compilar):
  // El uso de Optional + streams anidados + reduce no aporta claridad aquí.
  public void printSummary(final List<UserResponse> users) {
    if (users == null || users.isEmpty()) {
      console.println("  No users found.");
      return;
    }

    final StringBuilder summary = new StringBuilder();
    for (final UserResponse user : users) {
      summary.append(String.format("  %s (%s)%n", user.name(), getStatusLabel(user.status())));
    }
    console.println(summary.toString());
  }

  // Clean Code - Regla 16 (evitar condicionales repetitivas cuando el polimorfismo aporta claridad):
  // El mapa de etiquetas evita la cadena de if/else cuando se agregan nuevos estados.
  private static String getStatusLabel(final String status) {
    return STATUS_LABELS.getOrDefault(status, "Estado desconocido");
  }
}

