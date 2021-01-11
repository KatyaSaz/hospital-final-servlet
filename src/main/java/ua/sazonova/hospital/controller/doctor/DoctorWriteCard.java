package ua.sazonova.hospital.controller.doctor;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.service.LocalService;
import ua.sazonova.hospital.service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctor-patient-write")
public class DoctorWriteCard extends HttpServlet {
    private PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.DOCTOR_WRITE_RECORD_VIEW);
        String patId = req.getParameter(Const.PATIENT_ID);
        req.setAttribute(Const.PATIENT, patientService.getPatientById(patId, LocalService.getLanguage(req)));
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        String land = req.getParameter(Const.SESSION_LOCALE);
        String patientId = req.getParameter(Const.PATIENT_ID);
        int patId = Integer.valueOf(patientId);
        String description_en = req.getParameter(Const.CARD_DESCRIPTION_EN);
        String description_ru = req.getParameter(Const.CARD_DESCRIPTION_RU);
        String type = req.getParameter(Const.CARD_TYPE);
        patientService.createCardRecord(description_en, description_ru, type, patId);
        resp.sendRedirect("/doctor-patient?"+Const.PATIENT_ID+"="+patId+"&sessionLocale="+ land);
    }
}
