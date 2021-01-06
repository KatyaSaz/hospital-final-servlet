package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {
    void create(User user);
    void makeUserActive(int id);
    void delete(int id, Connection connection) throws SQLException;
    User getById(int id, Connection connection);
    int getIdOfUser(int id, String request);
    User findByEmail(String email);
    User getAdmin();
}
