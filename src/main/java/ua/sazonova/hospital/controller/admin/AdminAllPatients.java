package ua.sazonova.hospital.controller.admin;

import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.AdminService;

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
        req.setAttribute("patients", adminService.getAllPatients());
        req.setAttribute("doctors", adminService.getAllDoctors());
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String sortField = req.getParameter("sortField");
        String sortDirection = req.getParameter("sortDirection");
        if(sortField!=null&&sortDirection!=null){
            RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_PATIENTS_VIEW);
            req.setAttribute("patients", adminService.sortPatients(sortField, sortDirection));
            req.setAttribute("doctors", adminService.getAllDoctors());
            req.setAttribute("fieldS", sortField);
            req.setAttribute("directS", sortDirection);
            rd.forward(req, resp);
        }

        String newDocId = req.getParameter("newDocId");
        String patId = req.getParameter("patId");
        if(newDocId!=null && patId!=null){
            adminService.changeDoctorForPatient(patId, newDocId);
        }
        String deletePatId = req.getParameter("deletePatId");
        if(deletePatId!=null){
            adminService.deletePatient(deletePatId);
        }
        resp.sendRedirect("./admin-patients");
    }
}
