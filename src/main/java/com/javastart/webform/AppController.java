package com.javastart.webform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    private final MailService mailService;

    public AppController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/form")
    public String sendForm(Model model) {
        Email email = new Email();
        model.addAttribute("email", email);

        return "form";
    }

    @PostMapping("/form")
    private String send(Email email) {
        mailService.sendEmail(email);
        return "redirect:/";
    }

    @GetMapping("/about")
    public String info() {
        return "about";
    }
}
