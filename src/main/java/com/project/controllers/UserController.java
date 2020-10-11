package com.project.controllers;

import com.project.entities.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/addUser")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String addUser(@Valid @ModelAttribute(name = "user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
            model.addAttribute("errors", errorsMap);
            model.addAttribute("user", user);
            return "addUser";
        }
        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "redirect:/createProject";
    }
}
