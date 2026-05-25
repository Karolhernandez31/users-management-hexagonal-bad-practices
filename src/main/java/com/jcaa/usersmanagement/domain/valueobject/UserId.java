package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

import com.jcaa.usersmanagement.domain.exception.InvalidUserIdException;

public record UserId(String value) {

  public UserId {
    final String normalizedValue = Objects.requireNonNull(value, "UserId cannot be null").trim();
    validateNotEmpty(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidUserIdException.becauseValueIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
