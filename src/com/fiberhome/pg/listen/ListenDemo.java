package com.fiberhome.pg.listen;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @description: 描述
 * @author: ws
 * @time: 2020/9/1 16:28
 */
public class ListenDemo {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://172.16.108.7:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
            // Create two distinct connections, one for the notifier
            // and another for the listener to show the communication
            // works across connections although this example would
            // work fine with just one connection.
            Connection lConn = DriverManager.getConnection(URL,USER,PASSWORD);

            // Create two threads, one to issue notifications and
            // the other to receive them.
            Listener listener = new Listener(lConn);
            listener.run();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
