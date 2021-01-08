package ua.sazonova.hospital.controller;

import org.mindrot.jbcrypt.BCrypt;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.service.AuthorizationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginController extends HttpServlet {
    private static final String LOGIN_FORM="WEB-INF/view/login.jsp";
    private AuthorizationService authorizationService = new AuthorizationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(LOGIN_FORM);
        System.out.println(BCrypt.hashpw("admin", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("doctor", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("patient", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("xie_lian", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("flowEr", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("bin_pup", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("mitchel", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("master_mu", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("kot_yul", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("viki-jin", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("krot", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("heXuan", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("barb55", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("d2fire", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("tartaletka", BCrypt.gensalt()));

//        boolean isPasswordValid = BCrypt.checkpw(password, hashPassword);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + " " + password);
        User user = authorizationService.findUser(username);
        System.out.println(BCrypt.checkpw(password, user.getPassword()));
//        if (user != null) {
//            System.out.println("db " + user.getPassword());
//            String try_pw = BCrypt.hashpw(password, BCrypt.gensalt());
//            System.out.println("input_pass: "+try_pw);
////            BCrypt.gensalt(12);
////            (password.getBytes().toString(), user.getPassword());
//            //System.out.println("myCHECK:"+ myCHECK(password, user.getPassword()));
//
//            if (BCrypt.checkpw(password.trim(), user.getPassword().trim())) {
//                // put user into session
//                if (user.getRole().equals(Role.DOCTOR)) {
//                    System.out.println("in doctor");
//                    resp.sendRedirect("/doctor?docId=" + user.getIdMoreInfo());
//                } else if (user.getRole().equals(Role.PATIENT)) {
//                    System.out.println("in patient");
//                    resp.sendRedirect("/patient?patId=" + user.getIdMoreInfo());
//                } else if (user.getRole().equals(Role.ADMIN)) {
//                    System.out.println("in admin");
//                    resp.sendRedirect("/admin");
//                }
//            } else {
//                System.out.println("password wrong");
//                // the password is incorrect
//            }
//        } else {
//            System.out.println("no such user");
//            //no user with such login and password
//        }
    }


}
