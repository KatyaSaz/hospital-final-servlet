package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.service.CardRecordDAO;
import ua.sazonova.hospital.service.DoctorDAO;
import ua.sazonova.hospital.service.FactoryDAO;
import ua.sazonova.hospital.service.PatientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DoctorController extends HttpServlet {
    private static final String DOCTOR_FORM="WEB-INF/view/doctor/index.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;
    private DoctorDAO doctorDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(DOCTOR_FORM);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        doctorDAO = factoryDAO.getDoctorDAO();
        String docID = req.getParameter("docId");
        Doctor doctor = (docID!=null)? doctorDAO.getById(Integer.valueOf(docID)): null;
        //List<Patient> patientList = doctor.getPatients();
        req.setAttribute("doctor", doctor);
        //req.setAttribute("patients", patientList);
        rd.forward(req, resp);
    }
}
