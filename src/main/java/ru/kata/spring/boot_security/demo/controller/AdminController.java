package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        model.addAttribute("roleList", roleService.getRoleList());
        return "admin";
    }

//    @GetMapping("/updateUser")
//    public String updateUser(@RequestParam("userId") long id, Model model) {
//        model.addAttribute(userService.getUserById(id));
//        return "user-info";
//    }
//    @GetMapping("/saveUser")
//    public String saveUser(@ModelAttribute("user") User user) {
//        userService.addUser(user);
//        return "redirect:/";
//    }
//
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user-info";
    }

    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/addRole")
    public String addRole(Model model) {
        model.addAttribute("role", new Role());
        return "role-info";
    }
    @GetMapping("/saveRole")
    public String saveRole(@ModelAttribute("role") Role role) {
        roleService.saveRole(role);
        return "redirect:/admin";
    }

    @GetMapping("/deleteRole")
    public String deleteRole(@RequestParam("roleId") Long id) {
        roleService.deleteRole(id);
        return "redirect:/admin";
    }


}
