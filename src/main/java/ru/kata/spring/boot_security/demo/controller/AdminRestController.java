package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/admin/users")
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

//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.findAll();
//        return ResponseEntity.ok(users);
//    }

//    @GetMapping("")
//    @ResponseBody
//    public List<UserDTO> getAllUsers() {
//        return userService.findAll()
//                .stream()
//                .map(mapper::toDto)
//                .collect(toList());
//    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/{id}")
//    @ResponseBody
//    public UserDTO getUser(@PathVariable("id") Long id) {
//        UserDTO user = mapper.toDto(userService.getUserById(id));
//        return user;
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
//        UserDTO user = mapper.toDto(userService.getUserById(id));
//        return ResponseEntity.ok(user);
//    }
    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
//    @PostMapping("/users")
//    @ResponseBody
//    public UserDTO addNewUser(@RequestBody UserCreationDTO userDTO) {
//        User user = mapper.toUser(userDTO);
//        for (String role : userDTO.getRoles()) {
//            user.addRolesToUser(roleService.findByName(role));
//        }
//        userDTO.getRoles()
//                .stream()
//                .map(role -> roleService.(role))
//                .forEach(user::addRolesToUser);
//        userService.saveUser(user);
//        return mapper.toDto(userService.findByName(user.getUsername()));
//    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }
//    @PutMapping("/users")
//    @ResponseBody
//    public UserDTO updateUser(@RequestBody User user) {
//        userService.updateUser(user);
//        return mapper.toDto(userService.getUserById(user.getId()));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    //    @CrossOrigin() не работает
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUserById(id);
//    }

}
