package ua.sazonova.hospital.controller.registration;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.entity.extend.DoctorExtend;
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
        String name_en = req.getParameter(Const.NAME_EN);
        String surname_en = req.getParameter(Const.SURNAME_EN);
        String name_ru = req.getParameter(Const.NAME_RU);
        String surname_ru = req.getParameter(Const.SURNAME_RU);
        String type = req.getParameter(Const.DOC_TYPE);
        String experience = req.getParameter(Const.DOC_EXPERIENCE);
        DoctorExtend doctorExtend = new DoctorExtend(
                name_en, surname_en, DoctorType.valueOf(type),
                Integer.valueOf(experience), user, name_ru, surname_ru);
        adminService.createDoctor(doctorExtend);
        resp.sendRedirect("/registration-success");
    }
}
