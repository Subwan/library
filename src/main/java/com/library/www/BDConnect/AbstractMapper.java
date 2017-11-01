package com.library.www.BDConnect;

import com.library.www.Model.Book;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractMapper {


    private  DataSource connect() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        try {
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            dataSource.setValidationQuery("SELECT 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    private SqlSessionFactory dataSource() throws SQLException {
        DataSource dataSource = connect();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development",
                transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        sqlSessionFactory.getConfiguration().addMapper(BookMapper.class);

        return sqlSessionFactory;

    }

    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            SqlSessionFactory sqlSessionFactory = dataSource();
            SqlSession session = sqlSessionFactory.openSession();
            BookMapper mapper = session.getMapper(BookMapper.class);
            books = mapper.selectAllBook();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean insertBook(Book book) {
        boolean success = false;
        try {
            SqlSessionFactory sqlSessionFactory = dataSource();
            SqlSession session = sqlSessionFactory.openSession();
            BookMapper mapper = session.getMapper(BookMapper.class);
            success = mapper.insertBook(book.getName(), java.sql.Date.valueOf(book.getDate()), book.getAvailability());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteBook(long id) {
        boolean success = false;
        try {
            SqlSessionFactory sqlSessionFactory = dataSource();
            SqlSession session = sqlSessionFactory.openSession();
            BookMapper mapper = session.getMapper(BookMapper.class);
            success = mapper.deleteBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean updateBook(Book book) {
        boolean success = false;
        try {
            SqlSessionFactory sqlSessionFactory = dataSource();
            SqlSession session = sqlSessionFactory.openSession();
            BookMapper mapper = session.getMapper(BookMapper.class);
            success = mapper.updateBook(book.getId(), book.getName(), java.sql.Date.valueOf(book.getDate()), book.getAvailability());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
