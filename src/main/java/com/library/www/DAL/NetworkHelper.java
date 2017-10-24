package com.library.www.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class NetworkHelper {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("--TODO");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your JDBC Driver?");
            e.printStackTrace();
            return null;
        }
        String url = "jdbc:--TODO--";

        Properties props = new Properties();
        props.setProperty("user", "--TODO--");
        props.setProperty("password", "--TODO--");
        return DriverManager.getConnection(url, props);



    }
}
