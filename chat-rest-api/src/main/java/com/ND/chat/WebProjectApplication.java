package com.ND.chat;

import com.ND.chat.controllers.RequestsController;
import com.ND.chat.repository.MessagesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class WebProjectApplication {

    public static void main(String[] args) {
        try {
            RequestsController.messages = MessagesRepository.getMessage(15, "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        SpringApplication.run(WebProjectApplication.class, args);
    }

}
