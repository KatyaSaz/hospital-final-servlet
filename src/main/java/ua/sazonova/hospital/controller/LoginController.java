package ua.sazonova.hospital.controller;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private static final String LOGIN_FORM="WEB-INF/view/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(LOGIN_FORM);
//        boolean isPasswordValid = BCrypt.checkpw(password, hashPassword);
        rd.forward(req, resp);
    }

}
