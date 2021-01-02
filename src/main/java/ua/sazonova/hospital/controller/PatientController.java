package ua.sazonova.hospital.controller;

import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.service.CardRecordDAO;
import ua.sazonova.hospital.service.FactoryDAO;
import ua.sazonova.hospital.service.PatientDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpHeaders;

public class PatientController extends HttpServlet {
    private static final String PATIENT_FORM="WEB-INF/view/patient/info.jsp";
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;
    private PatientDAO patientDAO;
    private CardRecordDAO cardRecordDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(PATIENT_FORM);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        patientDAO = factoryDAO.getPatientDAO();
        String patientId = req.getParameter("patId");
        Patient patient = null;
        if(patientId!=null){
            patient = patientDAO.getById(Integer.valueOf(patientId));
        }
        req.setAttribute("patient", patient);
        rd.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String logOUT = req.getParameter("logOUT");
        System.out.println(logOUT);
        String saveCardId = req.getParameter("saveCardId");
        System.out.println(saveCardId);
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        cardRecordDAO = factoryDAO.getCardRecordDAO();
        CardRecord cr = cardRecordDAO.getByID(Integer.valueOf(saveCardId));
        System.out.println(cr);
        resp.setContentType("text");

        resp.setHeader("Content-disposition", "attachment;filename=" + cr.getFileName());
        try (BufferedOutputStream outStream = new BufferedOutputStream(resp.getOutputStream())) {
            byte[] buffer = cr.diagnoseToString().getBytes();
            outStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
