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


public class BookMapper extends AbstractMapper {

    private final static String TABLE_NAME = "books";
    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String DATE = "date";
    private final static String AVAILABILITY = "availability";

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
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
        return books;
    }

    @Override
    public boolean insertBook(Book book) {
        boolean rowInput = false;
        String sql = String.format("insert into %s (%s,%s,%s) values (?, ?, ?);",
                TABLE_NAME, NAME, DATE, AVAILABILITY);
        PreparedStatement ps = getPrepareStatement(sql);

        try {
            ps.setString(1, book.getName());
            ps.setDate(2, java.sql.Date.valueOf(book.getDate()));
            ps.setBoolean(3, book.getAvailability());
            rowInput = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }
        return rowInput;
    }

    @Override
    public boolean updateBook(Book book) {
        boolean rowUpdate = false;
        String sql = String.format("update %s set %s = ?, %s = ?, %s = ? where %s = ?;",
                TABLE_NAME, NAME, DATE, AVAILABILITY, ID);
        PreparedStatement ps = getPrepareStatement(sql);
        try {
            ps.setString(1, book.getName());
            ps.setDate(2, java.sql.Date.valueOf(book.getDate()));
            ps.setBoolean(3, book.getAvailability());
            ps.setLong(4, book.getId());
            rowUpdate = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }
        return rowUpdate;
    }

    @Override
    public boolean deleteBook(long id) {
        boolean rowUpdate = false;
        String sql = String.format("delete from %s where %s = ?", TABLE_NAME, ID);
        PreparedStatement ps = getPrepareStatement(sql);
        try {
            ps.setLong(1, id);
            rowUpdate = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }
        return rowUpdate;
    }


}
