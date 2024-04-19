package com.naham.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AdminHelpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter pw = resp.getWriter()) {
            pw.println("""
                    <!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
                    <html><head>
                    <title>403 Forbidden</title>
                    </head><body>
                    <h1>Forbidden</h1>
                    <p>You don't have permission to access this resource.</p>
                    </body></html>""");
        }
    }
}