package com.jcaa.usersmanagement.infrastructure.config;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.experimental.UtilityClass;

// VIOLACIÓN Regla 4: clase con solo métodos estáticos que NO tiene @UtilityClass ni constructor privado.
// Debería anotarse con @UtilityClass para evitar instanciación accidental y generar el constructor privado automáticamente.
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
