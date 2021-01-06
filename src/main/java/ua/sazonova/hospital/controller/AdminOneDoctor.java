package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.dao.FactoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminOneDoctor extends HttpServlet {
    private static final String ADMIN_ONE_DOCTOR_FORM="WEB-INF/view/admin/oneDoctor.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(ADMIN_ONE_DOCTOR_FORM);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        String docID = req.getParameter("docId");
        Doctor doctor = (docID!=null)? factoryDAO.getDoctorDAO().getById(Integer.valueOf(docID)): null;
        req.setAttribute("doctor", doctor);
        rd.forward(req, resp);
    }
}
