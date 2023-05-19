package ru.kata.spring.boot_security.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = ?1")
    User loadUserWithRoles(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles")
    List<User> loadAllUsersWithRoles();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = ?1")
    User loadUserWithRolesById(Long id);

    User findByLastName(String lastName);


//    @Modifying
//    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.isActive=?3, u.daysRemained=?4, u.username=?5, u.password=?6 where u.id = ?7")
//    void setUserInfoById(String firstname, String lastname, boolean isActive, int days, String username, String password, Long userId);
//
}
