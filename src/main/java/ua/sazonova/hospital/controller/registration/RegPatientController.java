package ua.sazonova.hospital.controller.registration;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Gender;
import ua.sazonova.hospital.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration-patient")
public class RegPatientController extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(View.REG_PATIENT_VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        User user = (User) req.getSession().getAttribute(Const.USER_REG);
        req.getSession().setAttribute(Const.USER_REG, null);
        String name = req.getParameter(Const.NAME);
        String surname = req.getParameter(Const.SURNAME);
        String gender = req.getParameter(Const.PAT_GENDER);
        String year = req.getParameter(Const.PAT_YEAR);
        String phone = req.getParameter(Const.PAT_PHONE);
        Patient patient = new Patient(name, surname, Gender.valueOf(gender), Integer.valueOf(year), phone, user);
        adminService.createPatient(patient);
        resp.sendRedirect("/registration-success");
    }
}
