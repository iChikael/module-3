package com.codegym.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

    String loginForm = "<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>Login</title>\n" +
            "<style type=\"text/css\">\n" +
            "    .login {\n" +
            "        height:180px; width:230px;\n" +
            "        margin:0;\n" +
            "        padding:10px;\n" +
            "        border:1px #CCC solid;\n" +
            "    }\n" +
            "    .login input {\n" +
            "        padding:5px; margin:5px\n" +
            "    }\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<form method=\"post\">\n" +
            "    <div class=\"login\">\n" +
            "        <h2>Login</h2>\n" +
            "        <input  type=\"text\" name=\"username\" size=\"30\"  placeholder=\"Username\" />\n" +
            "        <input  type=\"password\" name=\"password\" size=\"30\" placeholder=\"Password\" />\n" +
            "        <input type=\"submit\" value=\"Sign in\"/>\n" +
            "    </div>\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(loginForm);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(loginForm);
        if ("admin".equals(username) && "admin".equals(password)) {
            out.println("<h1>Welcome " + username + " to the website</h1>");
        } else {
            out.println("<h1>Login Error</h1>");
        }

    }
}
