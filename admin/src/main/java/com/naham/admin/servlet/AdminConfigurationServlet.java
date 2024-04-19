package com.naham.admin.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

public class AdminConfigurationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        String fileName = req.getParameter("file");

        if (fileName == null || fileName.isEmpty()) {
            printFilesInDir(pw);
        } else {
            printFileContent(pw, fileName);
        }


        pw.close();
    }

    private void printFileContent(PrintWriter pw, String fileName) {
        // Get the path to the config directory
        String relativePath = getServletContext().getRealPath("/config"); // Change this to your config directory path

        // Create a file object for the requested file
        File file = new File(relativePath, fileName);

        // Check if the file exists and is a file (not a directory)
        if (file.exists() && file.isFile()) {
            // Read the content of the file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                pw.println("<html><body>");
                pw.println("<h2>Content of " + fileName + ":</h2>");
                String line;
                while ((line = reader.readLine()) != null) {
                    pw.println(line + "<br>");
                }
                if (fileName.contains("..") || fileName.contains("/") || fileName.contains("%2e")) {
                    pw.println("<h3>You find flag FLAG{lFi_VulN}</h3>");
                }
                pw.println("</body></html>");
            } catch (IOException e) {
                pw.println("Error reading file: " + e.getMessage());
            }
        } else {
            pw.println("<html><body>");
            pw.println("<h2>File not found: " + fileName + "</h2>");
            pw.println("</body></html>");
        }
    }

    private void printFilesInDir(PrintWriter pw) {
        ServletContext context = getServletContext();
        String relativePath = context.getRealPath("/config");
        System.out.println("relativePath = " + relativePath);
        File configDir = new File(relativePath);
        if (configDir.exists() && configDir.isDirectory()) {
            pw.println("<html><body>");
            pw.println("<h2>Files in Config Directory:</h2>");
            pw.println("<ul>");

            File[] files = configDir.listFiles();

            if (files == null || files.length == 0) {
                pw.println("</ul>");
                pw.println("</body></html>");
                return;
            }

            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String filePath = context.getContextPath() + "/configuration?file=" + fileName;
                    pw.println("<li><a href='" + filePath + "'>" + fileName + "</a></li>");
                }
            }
            pw.println("</ul>");
            pw.println("</body></html>");
        } else {
            pw.println("<html><body>");
            pw.println("<h2>Config directory does not exist or is not a directory.</h2>");
            pw.println("</body></html>");
        }
    }
}