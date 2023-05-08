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
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("userList", userService.getUserList());
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
    public String deleteUSer(@RequestParam("userId") long id) {
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







}
