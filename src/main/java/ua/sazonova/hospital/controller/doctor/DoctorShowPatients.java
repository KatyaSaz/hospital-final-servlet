package ua.sazonova.hospital.controller.doctor;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.service.AdminService;
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
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.DOCTOR_HIS_PATIENTS_VIEW);
        String docID = req.getParameter(Const.DOCTOR_ID);
        Doctor doctor= doctorService.getDoctorById(docID);
        req.setAttribute(Const.DOCTOR, doctor);
        req.setAttribute(Const.PATIENTS, doctorService.getPatientsOfDoctor(doctor));
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String docID = req.getParameter(Const.DOCTOR_ID);
        String sortField = req.getParameter(Const.SORT_FIELD);
        String sortDirection = req.getParameter(Const.SORT_DIRECTION);
        if(sortField!= null && sortDirection!=null){
            RequestDispatcher rd = req.getRequestDispatcher(View.DOCTOR_HIS_PATIENTS_VIEW);
            Doctor doctor = doctorService.getDoctorById(docID);
            req.setAttribute(Const.DOCTOR, doctor);
            req.setAttribute(Const.PATIENTS,
                    adminService.sortPatientsOfOneDoctor(docID, sortField, sortDirection));
            req.setAttribute(Const.FIELD_SAVED_VALUE, sortField);
            req.setAttribute(Const.DIRECTION_SAVED_VALUE, sortDirection);
            rd.forward(req, resp);
        }
    }
}
