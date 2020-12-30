package ua.sazonova.hospital.service.mySql;

import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.service.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserDAO implements UserDAO {
    private final String SELECT_USER_BY_EMAIL="SELECT * FROM `users` WHERE email=?";
    private final String SELECT_USER_BY_ID="SELECT * FROM `users` WHERE id=?";
    private final String INSERT_USER="";

    private MySqlFactoryDAO factoryDAO;

    public MySqlUserDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public User getById(int id, Connection connection) {
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user =new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setActive(rs.getBoolean("is_active"));
                user.setIdMoreInfo(rs.getInt("more_info_id"));
            }
            rs.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        Connection connection = factoryDAO.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user =new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setActive(rs.getBoolean("is_active"));
                user.setIdMoreInfo(rs.getInt("more_info_id"));
            }
            rs.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return user;
    }
}
