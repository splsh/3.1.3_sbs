package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    UserServiceImpl userServiceImpl;
    RoleServiceImpl roleServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }
    @GetMapping
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

//    @GetMapping
//    public String getAdminPage(Model model, Principal principal) {
//        model.addAttribute("admin", userServiceImpl.findByName(principal.getName()));
//        model.addAttribute("user", new User());
//
//        model.addAttribute("users", userServiceImpl.findAll());
//        model.addAttribute("roles", roleServiceImpl.getAllRoles());
//
//        return "admin";
//    }
//
//    @PostMapping("/editUser/{id}")
//    public String saveUpdateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
//        userServiceImpl.updateUser(user);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/add")
//    public String createUser(@ModelAttribute("user") User user) {
//        userServiceImpl.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/deleteUser/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userServiceImpl.deleteUserById(id);
//        return "redirect:/admin";
//    }

//    @GetMapping("/admin")
//    public String userList(Model model, Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        model.addAttribute("user", userServiceImpl.findByName(userDetails.getUsername()));
//        model.addAttribute("userList", userServiceImpl.findAll());
//        model.addAttribute("roleList", roleServiceImpl.getAllRoles());
//        return "admin";
//    }
//
//    @GetMapping("/updateUser")
//    public String updateUser(@RequestParam("userId") Long id, Model model) {
//        model.addAttribute(userServiceImpl.getUserById(id));
//        return "user-all-info";
//    }
//
//    @GetMapping("/saveChanges")
//    public String saveChanges(@ModelAttribute("user") User user, @RequestParam("role") String role) {
//        userServiceImpl.updateUser(user);
//        userServiceImpl.addRoleToUser(user.getId(), role);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/deleteUser")
//    public String deleteUser(@RequestParam("userId") Long id) {
//        userServiceImpl.deleteUserById(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/addUser")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        return "user-info";
//    }
//
//    @GetMapping("/createNewUser")
//    public String createNewUser(@ModelAttribute("user") User user) {
//        userServiceImpl.createNewUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/addRole")
//    public String addRole(Model model) {
//        model.addAttribute("role", new Role());
//        return "role-info";
//    }
//
//    @GetMapping("/saveRole")
//    public String saveRole(@ModelAttribute("role") Role role) {
//        roleServiceImpl.saveRole(role);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/deleteRole")
//    public String deleteRole(@RequestParam("roleId") Long id) {
//        roleServiceImpl.deleteRole(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/clearUserRoles")
//    public String clearUserRoles(@RequestParam("userId") Long id) {
//        userServiceImpl.clearUsersRolesById(id);
//        return "redirect:/admin";
//    }


}
