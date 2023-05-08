package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repo.RoleRepository;
import ru.kata.spring.boot_security.demo.repo.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    //    @Transactional
    public void saveUser(User user) {
        user.setUsername(user.getFirstName().concat(user.getLastName()));
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return;
        }
        // b - безопасность
//        user.setPassword(bCryptPasswordEncoder.encode(("b" + new Random().nextInt(999_9999 - 100_0000) + 100_0000)));
        user.setPassword(bCryptPasswordEncoder.encode("123"));
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        userRepository.save(user);
    }


}
