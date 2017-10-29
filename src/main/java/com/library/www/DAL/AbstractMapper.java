package com.library.www.DAL;

import com.library.www.Model.Book;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Abstract class for working with data base.
 */
public abstract class AbstractMapper {

    private BasicDataSource dataSource;
    private Connection conn;

    /**
     * Select all book from data base
     * @return list of the book
     */
    public abstract List<Book> findAllBooks();

    /**
     * Insert book to data base.
     * @param book
     * @return boolean. Return 'True' if insert in data base succeed.
     */
    public abstract boolean insertBook(Book book);

    /**
     * Update book in data base
     * @param book
     * @return boolean. Return 'True' if update book in data base succeed.
     */
    public abstract boolean updateBook(Book book);

    /**
     * Delete book from data base.
     * @param id
     * @return boolean. Return 'True' if delete book from data base succeed.
     */
    public abstract boolean deleteBook(long id);

    /**
     * Get connect to the data base.
     * @throws SQLException
     */
    protected void connect() throws SQLException{
        try {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            dataSource.setValidationQuery("SELECT 1");
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close connect to the data base
     * @throws SQLException
     */
    protected void disconnect() throws SQLException{
        if (conn !=null && !conn.isClosed()) {
            conn.close();
            dataSource = null;
        }
    }

    /**
     * Use method connect(), create prepareStatement.
     * @param sql - sql request
     * @return prepareStatement.
     */
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

    /**
     * Use method disconnect(), close prepareStatement.
     * @param ps prepareStatement.
     */
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
