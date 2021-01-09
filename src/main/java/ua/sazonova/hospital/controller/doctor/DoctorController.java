package ua.sazonova.hospital.controller.doctor;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.service.DoctorService;

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
        String lang = req.getParameter("sessionLocale");
        Doctor doc = null;
        if(lang==null || lang.equals(Const.EN)){
            doc=doctorService.getDoctorById(docID);
        }else if(lang.equals(Const.RU)){
            doc = doctorService.getRussianDoctor(docID);
        }
        System.out.println(lang);

//        String local = req.getSession().getAttribute(Const.SESSION_LOCALE).toString();
//        System.out.println("local: "+local);
        //String lang = req.getSession().getAttribute(Const.LANG).toString();
        //System.out.println("lang: "+lang);
//        if(local!=null &local.equals(Const.RU)){
//            System.out.println("ruskii");
//        }

//        Doctor doc = doctorService.getDoctorById(docID);
//        System.out.println(doc.getType());
//        System.out.println(doc.getType().getName_ru());
        req.setAttribute(Const.DOCTOR, doc);
        rd.forward(req, resp);
    }
}
