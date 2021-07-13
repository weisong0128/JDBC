package com.fiberhome.pg.listen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description: 监听者
 * @author: ws
 * @time: 2020/9/1 16:30
 */
public class Listener implements Runnable{
    private Connection conn;
    private org.postgresql.PGConnection pgconn;

    Listener(Connection conn) throws SQLException {
        this.conn = conn;
        this.pgconn = conn.unwrap(org.postgresql.PGConnection.class);
        Statement stmt = conn.createStatement();
        stmt.execute("LISTEN dba");
        stmt.close();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // issue a dummy query to contact the backend
                // and receive any pending notifications.
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1");
                rs.close();
                stmt.close();
                //接收通知
                org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
                if (notifications != null) {
                    for (int i=0; i<notifications.length; i++) {
                        System.out.println("listen name:" + notifications[i].getName() + "---listen content:" + notifications[i].getParameter());
                    }
                }

                // wait a while before checking again for new
                // notifications
                Thread.sleep(500);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    /*@Override
    public void run() {

    }*/





}
