package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repo.RoleRepository;
import ru.kata.spring.boot_security.demo.repo.UserRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void saveRole(Role role) {
        if (roleRepository.findByRoleName(role.getRoleName()) != null) {
            return;
        }
        roleRepository.save(role);
    }
//    public void addUserToRole(String name, Long id) {
//
//        Role role = roleRepository.findByName(name);
//        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//
//        role.getUsers().add(user);
//        roleRepository.save(role);
//    }

//    public void addUserToRole(String name, Long id) {
//        Role role = findByName(name);
//        role.getUsers().add(userRepository.findById(id).orElseGet(() -> new User()));
//        roleRepository.save(role);
//    }
    //        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

    public void addRoleByName(User user, String role) {
      user.getRoles().add(findByName(role));
    }

    public Role findByName(String name) {
        return roleRepository.findByRoleName(name);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(Long id) {
        if (roleRepository.findById(id).isPresent()) {
            roleRepository.deleteById(id);
        }
    }
}
