package com.jcaa.usersmanagement.application.service;

import java.util.List;

import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllUsersPort;
import com.jcaa.usersmanagement.domain.model.UserModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllUsersService implements GetAllUsersUseCase {

  private final GetAllUsersPort getAllUsersPort;

  @Override
  public List<UserModel> execute() {
    final List<UserModel> users = getAllUsersPort.getAll();
    if (users == null || users.isEmpty()) {
      return List.of();
    }
    return users;
  }
}
