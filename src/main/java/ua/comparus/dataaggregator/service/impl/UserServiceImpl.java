package ua.comparus.dataaggregator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.comparus.dataaggregator.config.DataSourceConfig;
import ua.comparus.dataaggregator.repository.DynamicUserRepository;
import ua.comparus.dataaggregator.service.UserService;
import ua.comparus.dataaggregator.service.dto.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final DataSourceConfig config;
    private final DynamicUserRepository repository;

    public List<UserResponse> getAllUsers() {
        return config.getDataSources().stream()
                .flatMap(ds -> repository.fetchUsers(ds).stream())
                .map(u -> new UserResponse(u.id(), u.username(), u.name(), u.surname()))
                .collect(Collectors.toList());
    }
}
