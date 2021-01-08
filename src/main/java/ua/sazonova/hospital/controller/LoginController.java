package ua.sazonova.hospital.controller;

import org.mindrot.jbcrypt.BCrypt;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.service.AuthorizationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private AuthorizationService authorizationService = new AuthorizationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.LOGIN_FORM_VIEW);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + " " + password);
        User user = authorizationService.findUser(username);
        System.out.println();
        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                // put user into session
                if (user.getRole().equals(Role.DOCTOR)) {
                    System.out.println("in doctor");
                    resp.sendRedirect("/doctor?docId=" + user.getIdMoreInfo());
                } else if (user.getRole().equals(Role.PATIENT)) {
                    System.out.println("in patient");
                    resp.sendRedirect("/patient?patId=" + user.getIdMoreInfo());
                } else if (user.getRole().equals(Role.ADMIN)) {
                    System.out.println("in admin");
                    resp.sendRedirect("/admin");
                }
            } else {
                req.getRequestDispatcher(View.LOGIN_ERROR_WRONG_PASSWORD_VIEW).forward(req, resp);
            }
        } else {
            req.getRequestDispatcher(View.LOGIN_ERROR_USER_NOT_FOUND_VIEW).forward(req, resp);
        }
    }
}
