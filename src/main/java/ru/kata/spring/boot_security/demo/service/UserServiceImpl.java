package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repo.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    UserRepository userRepository;
    RoleServiceImpl roleServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleServiceImpl, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleServiceImpl = roleServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    //    @Transactional
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return user;
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.loadUserWithRoles(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void clearUsersRolesById(Long id) {
        User user = getUserById(id);
        user.removesRolesFromUser();
        saveUser(user);
    }

    public void addRoleToUser(Long id, String role) {
        User user = getUserById(id);
        user.getRoles().add(roleServiceImpl.findByName(role));
        saveUser(user);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllWithRoles() {
        return userRepository.loadAllUsersWithRoles();
    }

    @Override
    public User getUserWithRolesById(Long id) {
        return userRepository.loadUserWithRolesById(id);
    }

    @Transactional
    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }


    @Transactional
    @Override
    public void updateUser(User user) {
        String pass = user.getPassword();
        if (pass.isEmpty()) {
            userRepository.findById(user.getId()).ifPresent(u -> user.setPassword(u.getPassword()));
        } else {
            user.setPassword(passwordEncoder.encode(pass));
        }
        userRepository.save(user);
    }

    public User findByName(String username) {
        return userRepository.findByEmail(username);
    }



//    @Transactional()
//    public void updateUser(User user) {
////        // Если использовать save/merge/merge + flush/merge + clear при добавлении роли, в лучшем случае он добавляет одну роль, удаляя все старые. В худшем выкидывает ошибки. Не вижу смысла переделывать.
////        Настройки транзакции тоже менял. Без new теперь тоже нельзя из-за того, что в этом классе нельзя никак сделать autowiring BCryptPasswordEncoder
//        userRepository.setUserInfoById(user.getFirstName(), user.getLastName(), user.getIsActive(), user.getDaysRemained(), user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), user.getId());

//    }

//    @Transactional
//    public void createNewUser(User user) {
//        user.setEmail(user.getFirstName().concat(user.getLastName()).concat("" + new Random().nextInt(999_99 - 100_00) * 5));
//        if (findByName(user.getUsername()) != null) {
//            return;
//        }
//        // b - безопасность
////        user.setPassword(bCryptPasswordEncoder.encode(("b" + new Random().nextInt(999_9999 - 100_0000) + 100_0000)));
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getFirstName()));
//        user.addRolesToUser(roleServiceImpl.findByName("ROLE_USER"));
////        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
//        userRepository.save(user);

//    }


}
