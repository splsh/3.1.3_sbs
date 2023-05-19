package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserCreationDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.mapper.Mapper;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:56814/") не работает
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;
    private Mapper mapper;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService, Mapper mapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<UserDTO> getUsers() {
        return userService.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public UserDTO getUser(@PathVariable("id") Long id) {
        UserDTO user = mapper.toDto(userService.getUserById(id));
        return user;
    }

    //    @CrossOrigin()
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/users")
    @ResponseBody
    public UserDTO create(@RequestBody UserCreationDTO userDTO) {
        User user = mapper.toUser(userDTO);
        for (String role : userDTO.getRoles()) {
            user.addRolesToUser(roleService.findByName(role));
        }
//        userDTO.getRoles()
//                .stream()
//                .map(role -> roleService.(role))
//                .forEach(user::addRolesToUser);
        userService.saveUser(user);
        return mapper.toDto(userService.findByName(user.getUsername()));
    }

    @PutMapping("/users")
    @ResponseBody
    public UserDTO updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return mapper.toDto(userService.getUserById(user.getId()));
    }
}
