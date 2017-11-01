package com.library.www.BDConnect;

import com.library.www.Model.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;


/**
 * Working with data base.
 */
public interface BookMapper {

    String TABLE_NAME = "books";
    String ID = "id";
    String NAME = "name";
    String DATE = "date";
    String AVAILABILITY = "availability";



    @Select("SELECT * FROM " + TABLE_NAME)
    List<Book> selectAllBook ();

    @Insert("INSERT INTO " + TABLE_NAME + " ( " + NAME + ", " + DATE + ", " + AVAILABILITY +") values (#{name}, #{date}, #{availability}")
    boolean insertBook(String name, Date date, boolean availability);


    @Update("update " + TABLE_NAME + " set " + NAME + "=#{name}, " + DATE + "=#{date}, " + AVAILABILITY + "=#{availability} where " + ID + " =#{id};")
    boolean updateBook(long id, String name, Date date, boolean availability);

    @Delete("delete from " + TABLE_NAME + " where " + ID + " =#{id};")
    boolean deleteBook (long id);


}
