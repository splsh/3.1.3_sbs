package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {

    User findByName(String username);
    User getUserById(Long id);
    void saveUser(User user);
    void deleteUserById(Long id);
    void updateUser(User user);
    List<User> findAll();

    List<User> findAllWithRoles();

    User getUserWithRolesById(Long id);
}
