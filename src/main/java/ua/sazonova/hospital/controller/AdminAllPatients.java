package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.PatientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminAllPatients extends HttpServlet {
    private static final String ADMIN_PATIENTS_FORM="WEB-INF/view/admin/showPatients.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;
    private PatientDAO patientDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(ADMIN_PATIENTS_FORM);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        patientDAO = factoryDAO.getPatientDAO();
        List<Patient> patients = patientDAO.getAll();
        for(Patient patient:patients){
            System.out.println(patient.getId()+"   "+ patient.getName());
        }
        List<Doctor> doctors = factoryDAO.getDoctorDAO().getAll();
        req.setAttribute("patients", patients);
        req.setAttribute("doctors", doctors);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newDoc = req.getParameter("newDoc");
        System.out.println(newDoc);
        String patId=req.getParameter("patId");
        System.out.println(patId);
        Patient patient = patientDAO.getById(Integer.valueOf(patId));
        patient.setDoctor(factoryDAO.getDoctorDAO().getById(Integer.valueOf(newDoc)));
        patientDAO.updateDoctor(patient);
        System.out.println("newDoc: "+patient.getDoctor().getId());
        RequestDispatcher rd = req.getRequestDispatcher(ADMIN_PATIENTS_FORM);
        resp.sendRedirect("./admin-patients");
    }
}
