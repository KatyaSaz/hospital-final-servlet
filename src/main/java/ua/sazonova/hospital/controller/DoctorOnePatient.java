package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctor-patient")
public class DoctorOnePatient extends HttpServlet {
    private PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.DOCTOR_ONE_PATIENT_VIEW);
        String patId = req.getParameter("patId");
        req.setAttribute("patient", patientService.getPatientById(patId));
//        req.getSession().setAttribute("ID", patient.getId());
//        req.getSession().setAttribute("doctorID", patient.getDoctor().getId());
        rd.forward(req, resp);
    }
}
