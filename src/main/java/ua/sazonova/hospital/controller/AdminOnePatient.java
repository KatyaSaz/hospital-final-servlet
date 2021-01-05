package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.service.FactoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminOnePatient extends HttpServlet {
    private static final String ADMIN_ONE_PATIENT_FORM="WEB-INF/view/admin/onePatient.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(ADMIN_ONE_PATIENT_FORM);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        String patId = req.getParameter("patId");
        Patient patient = (patId!=null)? factoryDAO.getPatientDAO().getById(Integer.valueOf(patId)): null;
        req.setAttribute("patient", patient);
        rd.forward(req, resp);
    }
}
