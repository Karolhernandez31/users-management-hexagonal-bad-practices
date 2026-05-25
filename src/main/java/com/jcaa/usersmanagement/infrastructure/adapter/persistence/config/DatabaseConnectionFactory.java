package com.jcaa.usersmanagement.infrastructure.adapter.persistence.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;

import lombok.experimental.UtilityClass;

// Utility class for database connection creation. Lombok @UtilityClass prevents instantiation
// and forces createConnection() to be static.
@UtilityClass
public class DatabaseConnectionFactory {

  public static Connection createConnection(final DatabaseConfig config) {
    try {
      return DriverManager.getConnection(
          config.buildJdbcUrl(), config.username(), config.password());
    } catch (final SQLException exception) {
      throw PersistenceException.becauseConnectionFailed(exception);
    }
  }
}
