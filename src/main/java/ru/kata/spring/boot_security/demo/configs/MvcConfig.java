package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/addUser").setViewName("user-info");
        registry.addViewController("/addRole").setViewName("role-info");
        registry.addViewController("/html").setViewName("html");

//        registry.addViewController("/logout").setViewName("logout");
    }
// он здесь потому что, в сервисе или секьюрити конфиге вызывало кольцо в создании банов, никакие другие решения не помогли
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .logout(logout -> logout
//                        .logoutUrl("/")
//                        .addLogoutHandler(new SecurityContextLogoutHandler())
//                );
//        return http.build();
//    }
}
