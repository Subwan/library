package com.library.www.DAL;

import com.library.www.Model.Book;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public abstract class AbstractMapper {

    private BasicDataSource dataSource;
    private Connection conn;

    public abstract List<Book> findAllBooks();

    public abstract boolean insertBook(Book book);

    public abstract boolean updateBook(Book book);

    public abstract boolean deleteBook(long id);

    protected void connect() throws SQLException{
        try {
            dataSource = new BasicDataSource();

            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
//            dataSource.setPassword("123");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            dataSource.setValidationQuery("SELECT 1");
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
