package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {
    int create(User user, Connection connection) throws SQLException;
    void updateMoreInfoId(int id, int moreId, Connection connection) throws SQLException;
    void delete(int id, Connection connection) throws SQLException;
    void makeUserActive(int id);
    int getIdOfUser(int id, String request);
    User getById(int id, Connection connection);
    User findByEmail(String email);
}
