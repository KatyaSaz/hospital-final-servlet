package ua.sazonova.hospital.controller;

import org.mindrot.jbcrypt.BCrypt;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private AuthorizationService authorizationService = new AuthorizationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(View.LOGIN_FORM_VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logOut = req.getParameter("LogOut");
        if(logOut!=null){
            System.out.println("SET NULL: "+req.getSession().getAttribute("USER"));
            req.getSession().setAttribute("USER", null);
            resp.sendRedirect("./login");
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username!=null & password!=null){
            User user = authorizationService.findUser(username);
            if (user != null) {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    req.getSession().setAttribute("USER", user);
                    if (user.getRole().equals(Role.DOCTOR)) {
                        resp.sendRedirect("/doctor?docId=" + user.getIdMoreInfo());
                    } else if (user.getRole().equals(Role.PATIENT)) {
                        resp.sendRedirect("/patient?patId=" + user.getIdMoreInfo());
                    } else if (user.getRole().equals(Role.ADMIN)) {
                        resp.sendRedirect("/admin");
                    }else if(!user.isActive()){
                        req.getRequestDispatcher(View.REG_SUCCESS_WAIT_FOR_ADMIN_TO_APPROVE).forward(req, resp);
                    }
                } else {
                    req.getRequestDispatcher(View.ERROR_WRONG_PASSWORD_VIEW).forward(req, resp);
                }
            } else {
                req.getRequestDispatcher(View.ERROR_USER_NOT_FOUND_VIEW).forward(req, resp);
            }
        }
    }
}
