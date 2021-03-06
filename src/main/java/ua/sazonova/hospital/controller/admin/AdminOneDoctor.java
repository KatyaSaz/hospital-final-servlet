package ua.sazonova.hospital.controller.admin;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.DoctorService;
import ua.sazonova.hospital.service.LocalService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-doctor")
public class AdminOneDoctor extends HttpServlet {
    private DoctorService doctorService = new DoctorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.ADMIN_ONE_DOCTOR_VIEW);
        String docID = req.getParameter(Const.DOCTOR_ID);
        req.setAttribute(Const.DOCTOR, doctorService.getDoctorById(docID, LocalService.getLanguage(req)));
        rd.forward(req, resp);
    }
}
