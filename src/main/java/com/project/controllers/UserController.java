package com.project.controllers;

import com.project.entities.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addUser")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping
//    public String addUser() {
//        return "/addUser";
//    }

    @PostMapping
    public String addUser(@RequestParam String firstName,
                          @RequestParam String lastName,
                          Model model) {
        User user = new User(firstName,lastName);
        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "redirect:/createProject";
    }
}
