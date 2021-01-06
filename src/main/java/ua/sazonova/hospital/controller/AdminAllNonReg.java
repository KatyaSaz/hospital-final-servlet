package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.dao.FactoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminAllNonReg extends HttpServlet {
    private static final String ADMIN_NON_REG="WEB-INF/view/admin/showNonReg.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(ADMIN_NON_REG);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        List<Doctor> doctors = factoryDAO.getDoctorDAO().getNonActive();
        List<Patient> patients = factoryDAO.getPatientDAO().getNonActive();
        req.setAttribute("doctors", doctors);
        req.setAttribute("patients", patients);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String docId  = req.getParameter("forRegDocId");
        if(docId!=null){
            int userID=factoryDAO.getDoctorDAO().getUserId(Integer.valueOf(docId));
            factoryDAO.getUserDAO().makeUserActive(userID);
            resp.sendRedirect("./admin-doctors");
        }
//        System.out.println("docId"+docId);
        String patId = req.getParameter("forRegPatId");
        if(patId!=null){
            int userID=factoryDAO.getPatientDAO().getUserId(Integer.valueOf(patId));
            factoryDAO.getUserDAO().makeUserActive(userID);
            resp.sendRedirect("./admin-patients");
        }
//
//        System.out.println("patID"+patId);
    }
}
