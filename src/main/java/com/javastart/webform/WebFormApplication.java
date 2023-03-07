package com.javastart.webform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebFormApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext config = SpringApplication.run(WebFormApplication.class, args);
        MailService mailService = config.getBean(MailService.class);



    }

}
