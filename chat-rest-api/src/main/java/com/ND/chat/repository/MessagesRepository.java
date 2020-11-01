package com.ND.chat.repository;

import com.ND.chat.models.Message;

import java.sql.*;
import java.util.ArrayList;

public class MessagesRepository {
    private static final String database = "jdbc:mysql://wo4zyz1bkj8brow9:cywemeqszf3ap1hg@ryfqldzbliwmq6g5.chr7pe7" +
            "iynqr.eu-west-1.rds.amazonaws.com:3306/qplt4cbuonquizcn";

    public static void save(Message m) throws SQLException {
        Connection connection = DriverManager.getConnection(database);
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into Messages\n" +
                "(Username, textcontent, timecontent)\n" +
                "values \n" +
                "(\"" + m.user + "\", \"" + m.text + "\", \"" + m.time + "\");");
    }

    public static ArrayList<Message> getMessage(int n, String user) throws SQLException {
        Connection connection = DriverManager.getConnection(database);
        Statement statement = connection.createStatement();
        ResultSet data;
        if(user.equals("")){
            data = statement.executeQuery("select * from Messages order by message_id desc " +
                    "limit " + n + ";");
        }else{
            data = statement.executeQuery("select * from Messages where username = " + user +
                    " order by message_id desc limit " + n + ";");
        };
        ArrayList<Message> result = new ArrayList<Message>();
        while(data.next()){
            Message newMessage = new Message(data.getString(3), data.getString(2), data.getString(4));
            result.add(0, newMessage);
        }
        return result;
    }
}
