package ua.comparus.dataaggregator.service;

import ua.comparus.dataaggregator.service.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
}
