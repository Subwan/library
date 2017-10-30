package com.library.www.Servlet;

import com.google.gson.Gson;
import com.library.www.BDConnect.BookMapper;
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

/**
 * Servlet for Library App
 */
public class LibraryServlet extends HttpServlet {

    private BookMapper bookMapper = new BookMapper();

    /**
     * Servlet method doPost;
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    /**
     * Servlet method doGet. Use URL parameter "method' to identify what user want to do.
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        response.setContentType("text/html;charset=utf-8");
        if (Objects.equals(method, "insert")) {
            boolean success = insertBook("superbook", LocalDate.of(2001, Month.JANUARY, 3), false);
            if (success) {
                response.getWriter().write("work");
            } else {
                response.getWriter().write("dont work");
            }
        } else if (Objects.equals(method, "viewall")) {
            String json = viewAllBooks();
            response.getWriter().write(json);
        }
    }

    /**
     * Uses method of BookMapper - insertBook to insert book.
     * @param name
     * @param date
     * @param availability
     * @return boolean. Return 'true' if insert succeed.
     */
    private boolean insertBook(String name, LocalDate date, boolean availability) {
        Book book = new Book(name, date, availability);
        return bookMapper.insertBook(book);
    }

    /**
     * Uses method of BookMapper - findAllBooks to view all book.
     * Convert this list to JSON format.
     * @return list of books at JSON format
     */
    private String viewAllBooks() {
        List<Book> books = bookMapper.findAllBooks();
        String json = new Gson().toJson(books);
        return json;
    }

    /**
     * Uses method of BookMapper - deleteBook to delete book.
     * @param id
     * @return boolean. Return 'true' if delete succeed.
     */
    private boolean deleteBook(long id) {
        return bookMapper.deleteBook(id);
    }

    /**
     * Uses method of BookMapper - updateBook to update book.
     * @param id
     * @param name
     * @param date
     * @param availability
     * @return boolean. Return 'true' if update succeed.
     */
    private boolean updateBook(long id, String name, LocalDate date, boolean availability) {
        Book book = new Book(id,name, date, availability);
        return bookMapper.updateBook(book);
    }
}
