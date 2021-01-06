package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.User;

import java.sql.Connection;

public interface UserDAO {
    void create(User user);
    void makeUserActive(int id);
    User getById(int id, Connection connection);
    int getIdOfUser(int id, String request);
    User findByEmail(String email);
    User getAdmin();
}
