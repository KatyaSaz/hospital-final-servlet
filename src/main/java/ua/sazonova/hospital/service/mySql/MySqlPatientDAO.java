package ua.sazonova.hospital.service.mySql;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.entity.enam.Gender;
import ua.sazonova.hospital.service.DoctorDAO;
import ua.sazonova.hospital.service.PatientDAO;
import ua.sazonova.hospital.service.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPatientDAO implements PatientDAO {

    private final String SELECT_PATIENTS_OF_ONE_DOCTOR="SELECT * FROM `patients` WHERE doc_id=?";
    private final String SELECT_PATIENT_BY_ID="SELECT * FROM `patients` AS pat INNER JOIN `doctors` AS doc ON pat.doc_id=doc.id WHERE pat.id=?";
    private final String SELECT_ALL="SELECT * FROM `patients`";
    private final String SELECT_NON_REGISTER = "SELECT pat.id, pat.name, pat.surname, pat.gender, pat.year, pat.phone, pat.doc_id, pat.user_id FROM users AS user LEFT JOIN patients AS pat ON user.id=pat.user_id WHERE user.role='PATIENT' AND user.is_active=false";

    private MySqlFactoryDAO factoryDAO;
//    private DoctorDAO doctorDAO;
//    private UserDAO userDAO;

    public MySqlPatientDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
//        doctorDAO = factoryDAO.getDoctorDAO();
//        userDAO = factoryDAO.getUserDAO();
    }

    @Override
    public void create(Patient object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Patient getById(int id) {
        Connection connection = factoryDAO.getConnection();
        Patient patient = null;
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PATIENT_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                patient = new Patient();
                patient.setId(rs.getInt("pat.id"));
                patient.setName(rs.getString("pat.name"));
                patient.setSurname(rs.getString("pat.surname"));
                patient.setGender(Gender.valueOf(rs.getString("gender")));
                patient.setYear(rs.getInt("year"));
                patient.setPhone(rs.getString("phone"));
                patient.setUser(factoryDAO.getUserDAO().getById(rs.getInt("pat.user_id"), connection));

                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doc.id"));
                doctor.setName(rs.getString("doc.name"));
                doctor.setSurname(rs.getString("doc.surname"));
                doctor.setType(DoctorType.valueOf(rs.getString("type")));
                doctor.setExperience(rs.getInt("experience"));
                doctor.setUser(factoryDAO.getUserDAO().getById(rs.getInt("doc.user_id"), connection));
                patient.setDoctor(doctor);
                //patient.setUser(userDAO.getById(rs.getInt("user_id"), connection));

                patient.setRecords(factoryDAO.getCardRecordDAO().getRecordOfOnePatient(patient, connection));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return patient;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        Connection connection = factoryDAO.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL)){
            while(rs.next()){
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setGender(Gender.valueOf(rs.getString("gender")));
                patient.setYear(rs.getInt("year"));
                patient.setPhone(rs.getString("phone"));
                patient.setUser(factoryDAO.getUserDAO().getById(rs.getInt("user_id"), connection));
                patient.setDoctor(factoryDAO.getDoctorDAO().getById(rs.getInt("doc_id")));
                patient.setRecords(factoryDAO.getCardRecordDAO().getRecordOfOnePatient(patient, connection));
                patients.add(patient);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> getNonActive() {
        List<Patient> patients = new ArrayList<>();
        Connection connection = factoryDAO.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_NON_REGISTER)){
            while(rs.next()){
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setGender(Gender.valueOf(rs.getString("gender")));
                patient.setYear(rs.getInt("year"));
                patient.setPhone(rs.getString("phone"));
                patient.setUser(factoryDAO.getUserDAO().getById(rs.getInt("user_id"), connection));
                patient.setDoctor(factoryDAO.getDoctorDAO().getById(rs.getInt("doc_id")));
                patient.setRecords(factoryDAO.getCardRecordDAO().getRecordOfOnePatient(patient, connection));
                patients.add(patient);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

//    private Doctor getDoctorInPatient(int docId){
//        Doctor doctor =
//        return doctor;
//
//    }

//    public List<Patient> getPatients(int docID, Connection connection){
//
//    }

    @Override
    public List<Patient> getPatientsOfOneDoctor(Doctor doctor, Connection conn) {
        List<Patient> patients = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(SELECT_PATIENTS_OF_ONE_DOCTOR)){
            ps.setInt(1, doctor.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Patient patient =new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setSurname(rs.getString("surname"));
                patient.setGender(Gender.valueOf(rs.getString("gender")));
                patient.setYear(rs.getInt("year"));
                patient.setPhone(rs.getString("phone"));
                patient.setUser(factoryDAO.getUserDAO().getById(rs.getInt("user_id"), conn));
                patient.setDoctor(doctor);
                patient.setRecords(factoryDAO.getCardRecordDAO().getRecordOfOnePatient(patient, conn));
                patients.add(patient);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }
}
