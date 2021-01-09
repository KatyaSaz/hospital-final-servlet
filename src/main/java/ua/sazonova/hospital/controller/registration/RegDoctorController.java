package ua.sazonova.hospital.controller.registration;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration-doctor")
public class RegDoctorController extends HttpServlet {
    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(View.REG_DOCTOR_VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Const.USER_REG);
        req.getSession().setAttribute(Const.USER_REG, null);
        String name=req.getParameter(Const.NAME);
        String surname=req.getParameter(Const.SURNAME);
        String type= req.getParameter(Const.DOC_TYPE);
        String experience = req.getParameter(Const.DOC_EXPERIENCE);
        Doctor doctor = new Doctor(name, surname, DoctorType.valueOf(type), Integer.valueOf(experience), user);
        adminService.createDoctor(doctor);
        resp.sendRedirect("/registration-success");
    }
}
