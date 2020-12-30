package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.service.FactoryDAO;
import ua.sazonova.hospital.service.PatientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientController extends HttpServlet {
    private static final String PATIENT_FORM="WEB-INF/view/patient/info.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;
    private PatientDAO patientDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(PATIENT_FORM);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        patientDAO = factoryDAO.getPatientDAO();
        String patientId = req.getParameter("patId");
        Patient patient = null;
        if(patientId!=null){
            patient = patientDAO.getById(Integer.valueOf(patientId));
        }
        req.setAttribute("patient", patient);
        rd.forward(req, resp);
        //super.doGet(req, resp);
    }
}
