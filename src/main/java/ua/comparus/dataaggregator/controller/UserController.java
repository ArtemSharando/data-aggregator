package ua.comparus.dataaggregator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.comparus.dataaggregator.service.UserService;
import ua.comparus.dataaggregator.service.dto.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users API")
public class UserController {

    private final UserService service;

    @GetMapping
    @Operation(summary = "Get all users from all data sources")
    public List<UserResponse> getUsers() {
        return service.getAllUsers();
    }
}
