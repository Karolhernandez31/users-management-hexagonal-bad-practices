package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

import com.jcaa.usersmanagement.domain.exception.InvalidUserNameException;

public record UserName(String value) {

  private static final int MINIMUM_LENGTH = 3;

  public UserName {
    final String normalizedValue = Objects.requireNonNull(value, "UserName cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidUserNameException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String normalizedValue) {
    // VIOLACIÓN Regla 10: magic number 3 — debería usarse una constante con nombre descriptivo
    if (normalizedValue.length() < 3) {
      throw InvalidUserNameException.becauseLengthIsTooShort(3);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
