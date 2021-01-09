package ua.sazonova.hospital.controller.admin;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-non-reg")
public class AdminAllNonReg extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_NON_REG_VIEW);
        req.setAttribute(Const.DOCTORS, adminService.getNonActiveDoctors());
        req.setAttribute(Const.PATIENTS, adminService.getNonActivePatients());
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String docId = req.getParameter(Const.REG_DOCTOR_ID);
        if (docId != null) {
            adminService.makeUserActiveForDoctor(docId);
            resp.sendRedirect("./admin-doctors");
        }

        String patId = req.getParameter(Const.REG_PATIENT_ID);
        if (patId != null) {
            adminService.makeUserActiveForPatient(patId);
            resp.sendRedirect("./admin-patients");
        }
    }
}
