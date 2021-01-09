package ua.sazonova.hospital.controller.registration;

import ua.sazonova.hospital.constants.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration-success")
public class RegSuccessController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(View.WAIT_FOR_ADMIN_TO_APPROVE).forward(req, resp);
    }
}
