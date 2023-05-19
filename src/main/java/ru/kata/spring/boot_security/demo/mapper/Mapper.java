package ru.kata.spring.boot_security.demo.mapper;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.UserCreationDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Mapper {
    public UserDTO toDto(User user) {
        Long id = user.getId();
        String name = user.getFirstName();
        String lastName = user.getLastName();
        int age = user.getAge();
        String email = user.getEmail();
        List<String> roles = null;
        if(user.getRoles() != null) {
        roles = user
                .getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(toList()); }

        return new UserDTO(id, name, lastName, age, email, roles);
    }

    public User toUser(UserCreationDTO userDTO) {
        return new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAge(), userDTO.getEmail(), userDTO.getPassword(), new ArrayList<>());
    }
}
