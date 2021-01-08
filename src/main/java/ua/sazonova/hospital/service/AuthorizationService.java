package ua.sazonova.hospital.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.sazonova.hospital.dao.DoctorDAO;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.PatientDAO;
import ua.sazonova.hospital.dao.UserDAO;
import ua.sazonova.hospital.entity.User;

public class AuthorizationService {

    public static final int MY_SQL = 1;
    private UserDAO userDAO;

    public AuthorizationService(){
        userDAO = FactoryDAO.getInstance(MY_SQL).getUserDAO();
    }

     public User findUser(String login){
        return userDAO.findByEmail(login);

    }

//    public boolean isUserExist(User user){
//        return (user!=null);
//
//    }

//    public boolean isPasswordCorrect(String inputPassword, String userPassword){
//        return BCrypt.checkpw(inputPassword, userPassword);
//    }
}
