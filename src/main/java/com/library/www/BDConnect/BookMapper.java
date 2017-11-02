package com.library.www.BDConnect;

import com.library.www.Model.Book;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.time.LocalDate;
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



    @ConstructorArgs({
            @Arg(column = "id", javaType = Long.class)
            ,@Arg(column = "name", javaType = String.class)
            ,@Arg(column = "date", javaType = Date.class)
            ,@Arg(column = "availability", javaType = Boolean.class)
    })
    @Select("SELECT * FROM " + TABLE_NAME)
    List<Book> findAllBooks();

    @Insert("INSERT INTO " + TABLE_NAME + " (" + NAME + ", " + DATE +
            ", " + AVAILABILITY +") values (#{name}, #{date}, #{availability});")
    boolean  insertBook(@Param("name")String name, @Param("date") Date date,
                        @Param("availability") boolean availability);


    @Update("update " + TABLE_NAME + " set " + NAME + "=#{name}, " + DATE +
            "=#{date}, " + AVAILABILITY + "=#{availability} where " + ID + "=#{id};")
    boolean updateBook(@Param("id") long id, @Param("name") String name,
                       @Param("date") Date date,
                       @Param("availability") boolean availability);

    @Delete("delete from " + TABLE_NAME + " where " + ID + " = #{id};")
    boolean deleteBook (@Param("id") long id);


}
