package ua.sazonova.hospital.dao.mySql;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlFactoryDAO extends FactoryDAO {

    public MySqlFactoryDAO() {
        try {
            Class.forName(Const.DB_DRIVER).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Const.DB_PATH_DB_NAME
                    + Const.DB_NAME_PASSWORD + Const.DB_ENCODING_SETTINGS);
        } catch (SQLException ex) {
            System.out.println(Const.DB_SQL_EXCEPTION + ex.getMessage());
            System.out.println(Const.DB_SQL_STATE + ex.getSQLState());
            System.out.println(Const.DB_VENDOR_ERROR + ex.getErrorCode());
        }
        return conn;
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAO(this);
    }

    @Override
    public CardRecordDAO getCardRecordDAO() {
        return new MySqlCardRecordDAO(this);
    }

    @Override
    public DoctorDAO getDoctorDAO() {
        return new MySqlDoctorDAO(this);
    }

    @Override
    public PatientDAO getPatientDAO() {
        return new MySqlPatientDAO(this);
    }

}
