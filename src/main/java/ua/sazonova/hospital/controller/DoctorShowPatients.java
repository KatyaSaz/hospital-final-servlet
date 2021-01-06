package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.DoctorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctor-patients")
public class DoctorShowPatients extends HttpServlet {
    private DoctorService doctorService = new DoctorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.DOCTOR_HIS_PATIENTS_VIEW);
        String docID = req.getParameter("docId");
        req.setAttribute("doctor", doctorService.getDoctorById(docID));
        rd.forward(req, resp);
    }
}
