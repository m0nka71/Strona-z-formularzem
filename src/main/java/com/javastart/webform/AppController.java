package com.javastart.webform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    private MailService mailService;

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
        model.addAttribute("receiver", email.getReceiver());
        model.addAttribute("email", email.getEmail());
        model.addAttribute("content", email.getContent());

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
