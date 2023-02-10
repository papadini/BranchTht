package com.test.branchtht.controller;


import com.test.branchtht.entity.FormattedUserDto;
import com.test.branchtht.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller()
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<FormattedUserDto> getUserByUserName(@PathVariable final String username) {

        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}
