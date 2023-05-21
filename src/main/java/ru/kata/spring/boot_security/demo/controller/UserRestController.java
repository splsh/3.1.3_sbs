package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.mapper.Mapper;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    UserServiceImpl userServiceImpl;
    @Autowired
    public UserRestController( UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
//
//    @GetMapping
//    public ResponseEntity<UserDTO> getUser(@AuthenticationPrincipal User user) {
//        return ResponseEntity.ok(new Mapper().toDto(user));
//
//    }

    @GetMapping()
    public ResponseEntity<UserDTO> getUser(Model model, Authentication authentication, Role role) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new Mapper().toDto(userServiceImpl.findByName(userDetails.getUsername())));
    }
//
//    @GetMapping
//    public ResponseEntity<User> getUser(@AuthenticationPrincipal User user) {
//        return ResponseEntity.ok(user);
//
//    }
}