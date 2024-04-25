package com.naham.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AdminUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter pw = resp.getWriter()) {
            pw.println("<html><body>");
            pw.println("<h2>ApiUsers Table</h2>");
            pw.println("<table border='1'>");
            pw.println("<tr><th>Username</th><th>Password</th></tr>");
            pw.println("<tr><td>api_admin</td><td>M7z£19W=6f[/</td></tr>");
            pw.println("</table>");
            pw.println("</body></html>");
        }
    }
}
