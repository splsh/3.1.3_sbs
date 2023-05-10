package ru.kata.spring.boot_security.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByLastName(String lastName);


    @Modifying
    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.isActive=?3, u.daysRemained=?4, u.username=?5, u.password=?6 where u.id = ?7")
    void setUserInfoById(String firstname, String lastname, boolean isActive, int days, String username, String password, Long userId);

}
