package com.dev.manicure.controller;

import com.dev.manicure.auth.AuthenticationResponse;
import com.dev.manicure.entity.User;
import com.dev.manicure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/auth/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(userService.authentication(user));
    }

    @GetMapping(value = "/user/list")
    public List<User> userList() {
        return userService.userList();
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User>  updateUser(@PathVariable Long id, @RequestBody User user) {
        user = userService.updateUser(id,user);
        return ResponseEntity.created(URI.create("/user" + user.getId())).body(user);
    }

    @DeleteMapping(value = "/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id){
        return userService.deleteUser(id);
    }
}
