package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAllPatients extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_PATIENTS_VIEW);
        req.setAttribute("patients", adminService.getAllPatients());
        req.setAttribute("doctors", adminService.getAllDoctors());
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String newDocId = req.getParameter("newDocId");
        String patId = req.getParameter("patId");
        adminService.changeDoctorForPatient(patId, newDocId);
        resp.sendRedirect("./admin-patients");
    }
}
