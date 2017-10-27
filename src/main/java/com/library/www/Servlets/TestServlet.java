package com.library.www.Servlets;

import com.library.www.DAL.BookMapper;
import com.library.www.Model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
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
            response.getWriter().write(Boolean.toString(success));
        } else if (Objects.equals(method, "viewall")) {
            response.getWriter().write(method);
        }
    }

    private boolean insertBook(String name, LocalDate date, boolean availability) {
        Book book = new Book(name, date, availability);
        return bookMapper.insertBook(book);
    }

    private void viewAllBook() {

    }
}
