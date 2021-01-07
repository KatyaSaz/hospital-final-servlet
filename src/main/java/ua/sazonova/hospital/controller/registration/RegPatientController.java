package ua.sazonova.hospital.controller.registration;

import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration-patient")
public class RegPatientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.REG_PATIENT_VIEW);
        User user = (User) req.getSession().getAttribute("USER_REG");
        req.getSession().setAttribute("USER_REG", null);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/registration-success");
    }
}
