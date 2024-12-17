package com.example.demo.controller;

import com.example.demo.controller.request.UserRegisterCommand;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User update(@RequestBody UserRegisterCommand command) {
        return userService.addUser(command);
    }

    @GetMapping("info")
    public User getInfo(@RequestHeader("UserId") String userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/info/byPhone")
    public User getInfoByPhone(@RequestParam String phone) {
        return userService.getUserPhoneNumber(phone);
    }
}
