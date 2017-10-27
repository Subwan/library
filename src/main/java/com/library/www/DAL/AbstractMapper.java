package com.library.www.DAL;

import com.library.www.Model.Book;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public abstract class AbstractMapper {

    private DataSource dataSource;
    private Connection conn;

    public abstract List<Book> findAllBooks();

    public abstract boolean insertBook(Book book);

    public abstract boolean updateBook(Book book);

    public abstract boolean deleteBook(long id);

    protected void connect() throws SQLException{
        try {
            dataSource = (DataSource) new InitialContext().lookup("jdbc/library");
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void disconnect() throws SQLException{
        if (conn !=null && !conn.isClosed()) {
            conn.close();
            dataSource = null;
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
