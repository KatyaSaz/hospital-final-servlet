package ua.sazonova.hospital.dao.mySql;

import ua.sazonova.hospital.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlFactoryDAO extends FactoryDAO {

    public MySqlFactoryDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital?"
                            + "user=root&password="
                            +"&useEncoding=true&characterEncoding=UTF-8");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
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
