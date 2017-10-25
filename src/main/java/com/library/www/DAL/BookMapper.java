package com.library.www.DAL;

import com.library.www.Model.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexandr on 24.10.17.
 */
public class BookMapper extends AbstractMapper {

    private final static String TABLE_NAME = "books";
    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String DATE = "date";
    private final static String AVAILABILITY = "availability";

    private final static String FIND_ALL_BOOKS = String.format("select  %s, %s, %s from %s;",
            NAME, DATE, AVAILABILITY, TABLE_NAME);

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<Book>();
        String sql = "select * from " + TABLE_NAME;
        PreparedStatement ps = getPrepareStatement(sql);
        try {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                long id = result.getLong(ID);
                String name = result.getString(NAME);
                Date date = result.getDate(DATE);
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                boolean abailability = result.getBoolean(AVAILABILITY);
                Book book = new Book(id, name, localDate, abailability);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }
    }

    @Override
    public void insertBook(Book book) {
        String sql = String.format("insert into %s (%s,%s,%s) values (?, ?, ?);",
                TABLE_NAME, NAME, DATE, AVAILABILITY);
        PreparedStatement ps = getPrepareStatement(sql);

        try {
            ps.setString(1, book.getName());
            ps.setDate(2, java.sql.Date.valueOf(book.getDate()));
            ps.setBoolean(3, book.getAvailability());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

    }

    @Override
    public boolean updateBook(Book book) {
        String sql = String.format("update %s set %s = ?, %s = ?, %s = ? where %s = ?;",
                TABLE_NAME, NAME, book.getName(), DATE, book.getDate(), AVAILABILITY, book.getAvailability(),
                ID, book.getId());
        int updateCount = saveInDataBase(sql);
        if (updateCount == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean deleteBook(long id) {
        String sql = String.format("delete from %s where %s = ?", TABLE_NAME, ID, id);
        int deleteCount = saveInDataBase(sql);
        if (deleteCount == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected Book doLoad(ResultSet result) {
       try {
           Date date = result.getDate(DATE);
           LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           return new Book(result.getLong(ID), result.getString(NAME),
                   localDate, result.getBoolean(AVAILABILITY));
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
    }
}
