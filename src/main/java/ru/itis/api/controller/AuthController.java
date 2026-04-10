package ru.itis.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.api.dto.RegistDTO;
import ru.itis.infrastructure.persistence.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("registrationDTO", new RegistDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registrationUser(@Valid @ModelAttribute("registrationDTO") RegistDTO request,
                                   BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.saveNewUser(request);
            return "redirect:/login?registered=true";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String getMainPage() {
        return "home";
    }
}
