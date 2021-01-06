package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.PatientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorOnePatient extends HttpServlet {
    private static final String ONE_PATIENT_FORM="WEB-INF/view/doctor/onePatient.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;
    private PatientDAO patientDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(ONE_PATIENT_FORM);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        patientDAO = factoryDAO.getPatientDAO();
        String patId = req.getParameter("patId");
        Patient patient = (patId!=null)? patientDAO.getById(Integer.valueOf(patId)): null;
        req.setAttribute("patient", patient);
        req.getSession().setAttribute("ID", patient.getId());
        req.getSession().setAttribute("doctorID", patient.getDoctor().getId());
        rd.forward(req, resp);
    }
}
