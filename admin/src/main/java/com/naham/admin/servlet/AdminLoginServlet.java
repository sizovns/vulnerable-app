package com.naham.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter pw = resp.getWriter()) {
            pw.println("<html><body>");
            pw.println("<h2>Login Form</h2>");
            pw.println("<form method='post'>");
            pw.println("Username: <input type='text' name='username'><br>");
            pw.println("Password: <input type='password' name='password'><br>");
            pw.println("<input type='submit' value='Login'>");
            pw.println("</form>");
            pw.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter pw = resp.getWriter()) {
            pw.println("<html><body>");
            pw.println("<script>alert('Username or password is incorrect');</script>");
            pw.println("<h2>Login Form</h2>");
            pw.println("<form method='post'>");
            pw.println("Username: <input type='text' name='username'><br>");
            pw.println("Password: <input type='password' name='password'><br>");
            pw.println("<input type='submit' value='Login'>");
            pw.println("</form>");
            pw.println("</body></html>");
        }
    }
}
