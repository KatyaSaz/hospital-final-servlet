package ua.sazonova.hospital.controller.admin;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.AdminService;
import ua.sazonova.hospital.service.Local;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-patients")
public class AdminAllPatients extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_PATIENTS_VIEW);
        req.setAttribute(Const.PATIENTS, adminService.getAllPatients(Local.getLanguage(req)));
        req.setAttribute(Const.DOCTORS, adminService.getAllDoctors(Local.getLanguage(req)));
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String sortField = req.getParameter(Const.SORT_FIELD);
        System.out.println(sortField);
        String sortDirection = req.getParameter(Const.SORT_DIRECTION);
        System.out.println(sortDirection);
        if (sortField != null && sortDirection != null) {
            RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_PATIENTS_VIEW);
            req.setAttribute(Const.PATIENTS, adminService.sortPatients(sortField, sortDirection, Local.getLanguage(req)));
            req.setAttribute(Const.DOCTORS, adminService.getAllDoctors(Local.getLanguage(req)));
            req.setAttribute(Const.FIELD_SAVED_VALUE, sortField);
            req.setAttribute(Const.DIRECTION_SAVED_VALUE, sortDirection);
            rd.forward(req, resp);
        }

        String newDocId = req.getParameter(Const.NEW_DOCTOR_ID);
        String patId = req.getParameter(Const.PATIENT_ID);
        if (newDocId != null && patId != null) {
            adminService.changeDoctorForPatient(patId, newDocId, Local.getLanguage(req));
        }
        String deletePatId = req.getParameter(Const.DELETE_PATIENT_ID);
        if (deletePatId != null) {
            adminService.deletePatient(deletePatId);
        }
        resp.sendRedirect("./admin-patients");
    }
}
