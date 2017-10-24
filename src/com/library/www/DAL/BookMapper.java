package com.library.www.DAL;

import com.library.www.BL.Book;

import java.sql.ResultSet;
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
    private final static String INSERT_BOOK = String.format("insert into %s (%s,%s,%s) values ('?',?,?);",
            TABLE_NAME, NAME, DATE, AVAILABILITY);
    private final static String UPDATE_BOOK = String.format("update %s set %s = ?, %s = ?, %s = ? where %s = ?;",
            TABLE_NAME, NAME, DATE, AVAILABILITY, ID);
    private final static String DELETE_BOOK = String.format("delete from %s where %s = ?", TABLE_NAME, ID);

    @Override
    public List<Book> findAllBooks(long id) {

        return null;
    }

    @Override
    public boolean insertBook(Book book){

    }

    @Override
    public boolean updateBook(Book book){

    }

    @Override
    public boolean deleteBook(long id){

    }

    @Override
    protected Book doLoad(ResultSet result){

    }
}
