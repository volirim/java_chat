package com.ND.chat.models;

public class Message {
    public String text;
    public String user;
    public String time;

    public Message(String text, String user, String time) {
        this.text = text;
        this.user = user;
        this.time = time;
    }
}
