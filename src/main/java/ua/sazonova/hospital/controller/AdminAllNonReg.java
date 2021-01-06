package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAllNonReg extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_NON_REG_VIEW);
        req.setAttribute("doctors", adminService.getNonActiveDoctors());
        req.setAttribute("patients", adminService.getNonActivePatients());
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String docId = req.getParameter("forRegDocId");
        if (docId != null) {
            adminService.makeUserActiveForDoctor(docId);
            resp.sendRedirect("./admin-doctors");
        }

        String patId = req.getParameter("forRegPatId");
        if (patId != null) {
            adminService.makeUserActiveForPatient(patId);
            resp.sendRedirect("./admin-patients");
        }
    }
}
