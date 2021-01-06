package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.dao.DoctorDAO;
import ua.sazonova.hospital.dao.FactoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorShowPatients extends HttpServlet {
    private static final String DOCTOR_FORM="WEB-INF/view/doctor/showMyPatients.jsp";
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
        System.out.println("doc"+ doctor);
        req.setAttribute("doctor", doctor);
        rd.forward(req, resp);
    }
}
