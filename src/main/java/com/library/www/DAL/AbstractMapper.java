package com.library.www.DAL;

import com.library.www.Model.Book;

import java.io.IOException;
import java.sql.*;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class AbstractMapper {

    private Connection conn;

    public abstract List<Book> findAllBooks();

    public abstract boolean insertBook(Book book);

    public abstract boolean updateBook(Book book);

    public abstract boolean deleteBook(long id);

    protected void connect() throws SQLException{
        if (conn == null || conn.isClosed()) {
            String url = "jdbc:--TODO--";

            Properties props = new Properties();
            props.setProperty("user", "--TODO--");
            props.setProperty("password", "--TODO--");
            conn = DriverManager.getConnection(url, props);
        }
    }

    protected void disconnect() throws SQLException{
        if (conn !=null && !conn.isClosed()) {
            conn.close();
        }
    }

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            connect();
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps){
        if (ps != null) {
            try {
                ps.close();
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
