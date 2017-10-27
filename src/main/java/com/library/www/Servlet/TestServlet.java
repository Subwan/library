package com.library.www.Servlet;

import com.google.gson.Gson;
import com.library.www.DAL.BookMapper;
import com.library.www.Model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;


public class TestServlet extends HttpServlet {

    BookMapper bookMapper = new BookMapper();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        response.setContentType("text/html;charset=utf-8");
        if (Objects.equals(method, "insert")) {
            boolean success = insertBook("superbook", LocalDate.of(2001, Month.JANUARY, 3), false );
            if (success) {
                response.getWriter().write("work");
            } else {
                response.getWriter().write("dont work");
            }
        } else if (Objects.equals(method, "viewall")) {
            String json = viewAllBook();
            response.getWriter().write(json);
        }
    }

    private boolean insertBook(String name, LocalDate date, boolean availability) {
        Book book = new Book(name, date, availability);
        return bookMapper.insertBook(book);
    }

    private String viewAllBook() {
        List<Book> books = bookMapper.findAllBooks();
        String json = new Gson().toJson(books);
        return json;
    }

    private boolean deleteBook(long id) {
        return bookMapper.deleteBook(id);
    }

    private boolean updateBook(long id, String name, LocalDate date, boolean availability) {
        Book book = new Book(id,name, date, availability);
        return bookMapper.updateBook(book);
    }
}
