package ua.sazonova.hospital.controller.doctor;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.service.DoctorService;
import ua.sazonova.hospital.service.Local;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctor")
public class DoctorController extends HttpServlet {
    private DoctorService doctorService = new DoctorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.DOCTOR_VIEW);
        String docID = req.getParameter(Const.DOCTOR_ID);
        Doctor doc = doctorService.getDoctorById(docID, Local.getLanguage(req));
        req.setAttribute(Const.DOCTOR, doc);
        rd.forward(req, resp);
    }
}
