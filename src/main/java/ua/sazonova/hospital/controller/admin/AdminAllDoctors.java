package ua.sazonova.hospital.controller.admin;

import ua.sazonova.hospital.constants.Sort;
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
        req.setAttribute("doctors", adminService.getAllDoctors());
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String deleteId = req.getParameter("deleteDocId");
        System.out.println(deleteId);
        if(deleteId!=null){
            adminService.deleteDoctor(deleteId);
            resp.sendRedirect("./admin-doctors");
        }

        String searchType = req.getParameter("searchType");
        if(searchType!=null){
            RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_DOCTORS_VIEW);
            req.setAttribute("doctors", adminService.getAllDoctorsBySameType(DoctorType.valueOf(searchType)));
            req.setAttribute("typeDoc", searchType);
            rd.forward(req, resp);
        }

        String sortField = req.getParameter("sortField");
        String sortDirection = req.getParameter("sortDirection");
        if(sortField!=null&&sortDirection!=null){
            RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_DOCTORS_VIEW);
            req.setAttribute("doctors", adminService.sortDoctors(sortField, sortDirection));
            req.setAttribute("fieldS", sortField);
            req.setAttribute("directS", sortDirection);
            rd.forward(req, resp);
        }
    }
}
