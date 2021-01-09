package ua.sazonova.hospital.controller;

import org.mindrot.jbcrypt.BCrypt;
import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.service.AuthorizationService;
import ua.sazonova.hospital.service.Local;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private AuthorizationService authorizationService = new AuthorizationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(View.LOGIN_FORM_VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logOut = req.getParameter(Const.LOG_OUT);
        if(logOut!=null){
            System.out.println("SET NULL: "+req.getSession().getAttribute(Const.USER));
            req.getSession().setAttribute(Const.USER, null);
            resp.sendRedirect("./login");
        }

        String username = req.getParameter(Const.EMAIL);
        String password = req.getParameter(Const.PASSWORD);
        if(username!=null & password!=null){
            User user = authorizationService.findUser(username);
            if (user != null) {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    req.getSession().setAttribute(Const.USER, user);
                    if (user.getRole().equals(Role.DOCTOR)) {
                        resp.sendRedirect("/doctor?"+Const.DOCTOR_ID+"=" + user.getIdMoreInfo()+"&sessionLocale="+ Local.getLanguage(req));
                    } else if (user.getRole().equals(Role.PATIENT)) {
                        resp.sendRedirect("/patient?"+Const.PATIENT_ID+"=" + user.getIdMoreInfo()+"&sessionLocale="+ Local.getLanguage(req));
                    } else if (user.getRole().equals(Role.ADMIN)) {
                        resp.sendRedirect("/admin");
                    }else if(!user.isActive()){
                        req.getRequestDispatcher(View.WAIT_FOR_ADMIN_TO_APPROVE).forward(req, resp);
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
