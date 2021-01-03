package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.service.DoctorDAO;
import ua.sazonova.hospital.service.FactoryDAO;
import ua.sazonova.hospital.service.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminController extends HttpServlet {
    private static final String ADMIN_FORM="WEB-INF/view/admin/index.jsp";
//    public static final int MY_SQL = 1;
//    private FactoryDAO factoryDAO;
//    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(ADMIN_FORM);
//        factoryDAO = FactoryDAO.getInstance(MY_SQL);
//        userDAO = factoryDAO.getUserDAO();
//        User admin = userDAO.getAdmin();
//        req.setAttribute("admin", admin);
        rd.forward(req, resp);
    }
}
