package ua.sazonova.hospital.controller.admin;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-doctors")
public class AdminAllDoctors extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_DOCTORS_VIEW);
        req.setAttribute(Const.DOCTORS, adminService.getAllDoctors());
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String deleteId = req.getParameter(Const.DELETE_DOCTOR_ID);
        System.out.println(deleteId);
        if(deleteId!=null){
            adminService.deleteDoctor(deleteId);
            resp.sendRedirect("./admin-doctors");
        }

        String searchType = req.getParameter(Const.SEARCH_TYPE_DOCTOR);
        if(searchType!=null){
            RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_DOCTORS_VIEW);
            req.setAttribute(Const.DOCTORS, adminService.getAllDoctorsBySameType(DoctorType.valueOf(searchType)));
            req.setAttribute(Const.SEARCH_SAVED_VALUE, searchType);
            rd.forward(req, resp);
        }

        String sortField = req.getParameter(Const.SORT_FIELD);
        String sortDirection = req.getParameter(Const.SORT_DIRECTION);
        if(sortField!=null&&sortDirection!=null){
            RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_DOCTORS_VIEW);
            req.setAttribute(Const.DOCTORS, adminService.sortDoctors(sortField, sortDirection));
            req.setAttribute(Const.FIELD_SAVED_VALUE, sortField);
            req.setAttribute(Const.DIRECTION_SAVED_VALUE, sortDirection);
            rd.forward(req, resp);
        }
    }
}
