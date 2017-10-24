package com.library.www.DAL;

import com.library.www.BL.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractMapper {

    public abstract List<Book> findAllBooks(long id);

    public abstract boolean insertBook(Book book);

    public abstract boolean updateBook(Book book);

    public abstract boolean deleteBook(long id);

    public int saveInDataBase(String sql) {
        try {
            Connection conn = NetworkHelper.getConnection();
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.executeUpdate();
            int count = preStatement.getUpdateCount();
            conn.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected Book loadFromDataBase(String sql) {
        try {
            Connection conn = NetworkHelper.getConnection();
            PreparedStatement preStatement = conn.prepareStatement(sql);
            ResultSet result = preStatement.executeQuery();
            Book book = null;
            if (result.next()) {
                book = doLoad(result);
            }
            conn.close();
            return book;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Book doLoad(ResultSet result);

}
