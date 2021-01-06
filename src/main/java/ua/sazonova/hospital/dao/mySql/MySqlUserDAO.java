package ua.sazonova.hospital.dao.mySql;

import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.dao.UserDAO;

import java.sql.*;

public class MySqlUserDAO implements UserDAO {
    private final String SELECT_USER_BY_EMAIL="SELECT * FROM `users` WHERE email=?";
    private final String SELECT_USER_BY_ID="SELECT * FROM `users` WHERE id=?";
    private final String SELECT_ADMIN="SELECT * FROM `users` WHERE role='ADMIN'";
    private final String INSERT_USER="";
    private  final String UPDATE_USER_ACTIVE = "UPDATE `users` SET `is_active`=? WHERE id=?";

    private MySqlFactoryDAO factoryDAO;

    public MySqlUserDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
    }

    @Override
    public void create(User user) {

    }

    public int getIdOfUser(int id, String request){
        int userId = -1;
        Connection connection = factoryDAO.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(request)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userId = rs.getInt("user_id");
            }
            rs.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userId;
    }

    @Override
    public void makeUserActive(int id) {
        Connection connection = factoryDAO.getConnection();
        try(PreparedStatement ps=connection.prepareStatement(UPDATE_USER_ACTIVE);){
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
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

    @Override
    public User getAdmin() {
        User user = null;
        Connection connection = factoryDAO.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ADMIN)){
            while(rs.next()){
                user =new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setActive(rs.getBoolean("is_active"));
                user.setIdMoreInfo(rs.getInt("more_info_id"));
            }
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
