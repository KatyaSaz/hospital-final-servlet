package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.service.DoctorDAO;
import ua.sazonova.hospital.service.FactoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminAllNonReg extends HttpServlet {
    private static final String ADMIN_NON_REG="WEB-INF/view/admin/showNonReg.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(ADMIN_NON_REG);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        List<Doctor> doctors = factoryDAO.getDoctorDAO().getNonActive();
        List<Patient> patients = factoryDAO.getPatientDAO().getNonActive();
        req.setAttribute("doctors", doctors);
        req.setAttribute("patients", patients);
        rd.forward(req, resp);
    }
}
