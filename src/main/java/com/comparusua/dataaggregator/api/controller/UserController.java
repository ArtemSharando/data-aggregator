package com.comparusua.dataaggregator.api.controller;

import com.comparusua.dataaggregator.api.dto.UserDto;
import com.comparusua.dataaggregator.api.dto.UserFilterDto;
import com.comparusua.dataaggregator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            Pageable pageable) {
        return userService.getAllUsers(pageable, new UserFilterDto(username, name, surname));
    }
}
