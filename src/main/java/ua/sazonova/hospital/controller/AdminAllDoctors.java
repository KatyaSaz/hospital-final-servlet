package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.service.DoctorDAO;
import ua.sazonova.hospital.service.FactoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminAllDoctors extends HttpServlet {
    private static final String ADMIN_DOCTORS_FORM="WEB-INF/view/admin/showDoctors.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;
    private DoctorDAO doctorDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(ADMIN_DOCTORS_FORM);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        doctorDAO = factoryDAO.getDoctorDAO();
        List<Doctor> doctors = doctorDAO.getAll();
        for(Doctor patient:doctors){
            System.out.println(patient.getId()+"   "+ patient.getName());
        }
        req.setAttribute("doctors", doctors);
        rd.forward(req, resp);
    }
}
