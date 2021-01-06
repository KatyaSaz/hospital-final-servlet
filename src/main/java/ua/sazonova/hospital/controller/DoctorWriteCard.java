package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.dao.FactoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorWriteCard extends HttpServlet {
    private static final String DOCTOR_WRITE_FORM="WEB-INF/view/doctor/writeRecord.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(DOCTOR_WRITE_FORM);
        String patId = req.getParameter("patId");
        req.setAttribute("patientID", patId);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        String patientID = req.getSession().getAttribute("ID").toString();
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        int patId = Integer.valueOf(patientID);
        factoryDAO.getCardRecordDAO().create(description,type,patId);
        resp.sendRedirect("./doc-patient?patId="+patId);
    }
}
