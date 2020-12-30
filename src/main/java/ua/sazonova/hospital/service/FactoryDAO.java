package ua.sazonova.hospital.service;

import ua.sazonova.hospital.service.mySql.MySqlFactoryDAO;

public abstract class FactoryDAO {

    public final static int MY_SQL = 1;

    public abstract UserDAO getUserDAO();
    public abstract CardRecordDAO getCardRecordDAO();
    public  abstract DoctorDAO getDoctorDAO();
    public abstract PatientDAO getPatientDAO();

    public static FactoryDAO getInstance(int sourceType) {
        switch (sourceType) {
            case MY_SQL:
                return new MySqlFactoryDAO();
            default:
                return null;
        }
    }



}
