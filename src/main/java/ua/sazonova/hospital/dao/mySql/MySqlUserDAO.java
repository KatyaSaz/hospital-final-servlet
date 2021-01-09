package ua.sazonova.hospital.dao.mySql;

import org.mindrot.jbcrypt.BCrypt;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.dao.UserDAO;

import java.sql.*;

public class MySqlUserDAO implements UserDAO {
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM `users` WHERE email=?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM `users` WHERE id=?";
    private static final String SELECT_USER_ID_BY_EMAIL = "SELECT `id` FROM `users` WHERE `email`=?";
    private static final String INSERT_USER = "INSERT INTO `users`(`email`, `password`, `role`, `is_active`) VALUES (?,?,?,?)";
    private static final String UPDATE_USER_ACTIVE = "UPDATE `users` SET `is_active`=? WHERE id=?";
    private static final String UPDATE_USER_MORE_INFO_ID = "UPDATE `users` SET `more_info_id`=? WHERE `id`=?";
    private static final String DELETE_USER = "DELETE FROM `users` WHERE id=?";

    private MySqlFactoryDAO factoryDAO;

    public MySqlUserDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
    }

    private int getIdOfUserByEmail(String email, Connection connection) throws SQLException {
        int userId = -1;
        PreparedStatement ps = connection.prepareStatement(SELECT_USER_ID_BY_EMAIL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            userId = rs.getInt("id");
        }
        return userId;
    }

    @Override
    public int create(User user, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_USER);
        ps.setString(1, user.getEmail());
        ps.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        ps.setString(3, user.getRole().toString());
        ps.setBoolean(4, user.isActive());
        ps.execute();
        ps.close();
        return getIdOfUserByEmail(user.getEmail(), connection);
    }

    @Override
    public void updateMoreInfoId(int id, int moreId, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_USER_MORE_INFO_ID);
        ps.setInt(1, moreId);
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(int id, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_USER);
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public void makeUserActive(int id) {
        Connection connection = factoryDAO.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_ACTIVE);) {
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public int getIdOfUser(int id, String request) {
        int userId = -1;
        Connection connection = factoryDAO.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(request)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("user_id");
            }
            rs.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userId;
    }

    private User setUpUserInfo(PreparedStatement ps) throws SQLException {
        User user = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            user.setActive(rs.getBoolean("is_active"));
            user.setIdMoreInfo(rs.getInt("more_info_id"));
        }
        rs.close();
        return user;
    }

    @Override
    public User getById(int id, Connection connection) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID)) {
            ps.setInt(1, id);
            user = setUpUserInfo(ps);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        Connection connection = factoryDAO.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            ps.setString(1, email);
            user = setUpUserInfo(ps);
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }
}
