package com.library.www.Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class TestServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        response.setContentType("text/html;charset=utf-8");
        if (Objects.equals(method, "insert")) {
            response.getWriter().write(method);
        } else if (Objects.equals(method, "viewall")) {
            response.getWriter().write(method);
        }
    }
}
