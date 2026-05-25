package com.jcaa.usersmanagement.infrastructure.config;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.experimental.UtilityClass;

// Utility provider for Jakarta Bean Validation.
// Lombok @UtilityClass prevents instantiation and exposes only static helpers.
@UtilityClass
public class ValidatorProvider {

  public static Validator buildValidator() {
    try (final ValidatorFactory factory = Validation.byDefaultProvider()
        .configure()
        .messageInterpolator(new ParameterMessageInterpolator())
        .buildValidatorFactory()) {
      return factory.getValidator();
    }
  }
}
