package ua.sazonova.hospital.controller.patient;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.service.LocalService;
import ua.sazonova.hospital.service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/patient")
public class PatientController extends HttpServlet {
    private PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(View.PATIENT_VIEW);
        String patientId = req.getParameter(Const.PATIENT_ID);
        req.setAttribute(Const.PATIENT,
                patientService.getPatientById(patientId, LocalService.getLanguage(req)));
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        String saveId = req.getParameter(Const.DOWNLOAD_CARD_ID);
        CardRecord cardRecord =
                patientService.getIdOfCardRecordToSave(saveId, Const.EN);
        resp.setContentType("text");
        resp.setHeader("Content-disposition", "attachment;filename=" + cardRecord.getFileName());
        try (BufferedOutputStream outStream = new BufferedOutputStream(resp.getOutputStream())) {
            byte[] buffer = cardRecord.diagnoseToString().getBytes();
            outStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
