package ua.sazonova.hospital.service;

import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.UserDAO;
import ua.sazonova.hospital.entity.User;

public class AuthorizationService {

    public static final int MY_SQL = 1;
    private UserDAO userDAO;

    public AuthorizationService() {
        userDAO = FactoryDAO.getInstance(MY_SQL).getUserDAO();
    }

    public User findUser(String login) {
        return userDAO.findByEmail(login);
    }
}
