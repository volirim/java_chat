package com.ND.chat.controllers;

import com.ND.chat.models.Message;
import com.ND.chat.repository.MessagesRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class RequestsController {

    public static ArrayList<Message> messages = new ArrayList<Message>();

    @RequestMapping("/put")
    public String put(@RequestParam(value="user", required=false, defaultValue="") String user,
                      @RequestParam(value="time", required=false, defaultValue="") String time,
                      @RequestParam(value="text", required=false, defaultValue="") String text) {
        try {
            MessagesRepository.save(new Message(text, user, time));
            messages.add(new Message(text, user, time));
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/get")
    public ArrayList<Message> get(@RequestParam(value = "user", required = false, defaultValue = "") String user) {
        try {
            if (user.equals("")){
                return messages;
            }else{
                return MessagesRepository.getMessage(15, user);
            }
        } catch (SQLException throwables) {
            return messages;
        }
    }
}
