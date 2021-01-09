package ua.sazonova.hospital.controller.registration;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.REG_USER_VIEW);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (email != null & password != null & role != null) {
            User user = new User(email, password, Role.valueOf(role));
            req.getSession().setAttribute(Const.USER_REG, user);
            if(user.getRole().equals(Role.PATIENT)){
                resp.sendRedirect("/registration-patient");
            }else if(user.getRole().equals(Role.DOCTOR)){
                resp.sendRedirect("./registration-doctor");
            }
        }
    }
}
