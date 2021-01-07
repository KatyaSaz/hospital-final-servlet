package ua.sazonova.hospital.controller.doctor;

import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.Doctor;
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
        Doctor doctor= doctorService.getDoctorById(docID);
        req.setAttribute("doctor", doctor);
        req.setAttribute("patients", doctorService.getPatientsOfDoctor(doctor));
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String docID = req.getParameter("docID");
        String sortField = req.getParameter("sortField");
        String sortDirection = req.getParameter("sortDirection");
        if(sortField!= null && sortDirection!=null){
            RequestDispatcher rd = req.getRequestDispatcher(View.DOCTOR_HIS_PATIENTS_VIEW);
            Doctor doctor= doctorService.getDoctorById(docID);
            req.setAttribute("doctor", doctor);
            req.setAttribute("patients",
                    doctorService.sortPatients(docID, sortField, sortDirection));
            //req.setAttribute("doctor", doctorService.getDoctorById(docID));
            req.setAttribute("fieldS", sortField);
            req.setAttribute("directS", sortDirection);
            rd.forward(req, resp);
        }
    }
}
