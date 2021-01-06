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

@WebServlet("/doctor-patient-write")
public class DoctorWriteCard extends HttpServlet {
    PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.DOCTOR_WRITE_RECORD_VIEW);
        String patId = req.getParameter("patId");
        req.setAttribute("patient", patientService.getPatientById(patId));
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        String patientId = req.getParameter("patientId");
        System.out.println("PAT_ID "+patientId);
        int patId = Integer.valueOf(patientId);
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        patientService.createCardRecord(description, type, patId);
        resp.sendRedirect("./doctor-patient?patId="+patId);
    }
}
