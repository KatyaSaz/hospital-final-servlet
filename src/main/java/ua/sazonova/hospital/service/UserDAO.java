package ua.sazonova.hospital.service;

import ua.sazonova.hospital.entity.User;

import java.sql.Connection;

public interface UserDAO {
    void create(User user);
    User getById(int id, Connection connection);
    User findByEmail(String email);
}
