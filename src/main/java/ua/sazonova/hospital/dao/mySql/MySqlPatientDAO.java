package ua.sazonova.hospital.dao.mySql;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.entity.enam.Gender;
import ua.sazonova.hospital.dao.PatientDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPatientDAO implements PatientDAO {

    private static final String SELECT_PATIENT_BY_ID="SELECT * FROM `patients` AS pat INNER JOIN `doctors` AS doc ON pat.doc_id=doc.id WHERE pat.id=?";
    private static final String SELECT_USER_ID = "SELECT user_id FROM `patients` WHERE id=?";
    private static final String SELECT_ID_BY_USER_ID = "SELECT `id` FROM `patients` WHERE `user_id`=?";
    private static final String SELECT_ALL="SELECT * FROM `patients`";
    private static final String SELECT_NON_REGISTER = "SELECT pat.id, pat.name, pat.surname, pat.gender, pat.year, pat.phone, pat.doc_id, pat.user_id FROM users AS user LEFT JOIN patients AS pat ON user.id=pat.user_id WHERE user.role='PATIENT' AND user.is_active=false";
    private static final String SELECT_PATIENTS_OF_ONE_DOCTOR="SELECT * FROM `patients` WHERE doc_id=?";
    private static final String INSERT_PATIENT="INSERT INTO `patients`(`name`, `surname`, `gender`, `year`, `phone`, `doc_id`, `user_id`) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_DOCTOR_IN_PATIENT = "UPDATE `patients` SET `doc_id`=? WHERE id=?";
    private static final String DELETE_PATIENT="DELETE FROM `patients` WHERE id=?";

    private MySqlFactoryDAO factoryDAO;

    public MySqlPatientDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
    }

    private int getIdOfPatientByUserId(int userId, Connection connection) throws SQLException {
        int patientId = -1;
        PreparedStatement ps = connection.prepareStatement(SELECT_ID_BY_USER_ID);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            patientId = rs.getInt("id");
        }
        return patientId;
    }

    private int createPatient(Patient patient, int docID, int userID, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_PATIENT);
        ps.setString(1, patient.getName());
        ps.setString(2, patient.getSurname());
        ps.setString(3, patient.getGender().toString());
        ps.setInt(4, patient.getYear());
        ps.setString(5, patient.getPhone());
        ps.setInt(6, docID);
        ps.setInt(7, userID);
        ps.execute();
        ps.close();
        return getIdOfPatientByUserId(userID, connection);
    }

    @Override
    public void create(Patient patient) {
        Connection connection = factoryDAO.getConnection();
        try {
            connection.setAutoCommit(false);
            int userID = factoryDAO.getUserDAO().create(patient.getUser(), connection);
            int patID = createPatient(patient, Doctor.DEFAULT_DOCTOR_ID, userID, connection);
            factoryDAO.getUserDAO().updateMoreInfoId(userID, patID, connection);
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private void deletePatient(int id, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_PATIENT);
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(int id) {
        Connection connection= factoryDAO.getConnection();
        try {
            connection.setAutoCommit(false);
            deletePatient(id, connection);
            factoryDAO.getUserDAO().delete(getUserId(id), connection);
            factoryDAO.getCardRecordDAO().deleteRecordsOfOnePatient(id, connection);
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public int getUserId(int id){
        return factoryDAO.getUserDAO().getIdOfUser(id, SELECT_USER_ID);
    }

    @Override
    public void updateDoctor(Patient patient) {
        Connection connection = factoryDAO.getConnection();
        try(PreparedStatement ps=connection.prepareStatement(UPDATE_DOCTOR_IN_PATIENT);){
            ps.setInt(1, patient.getDoctor().getId());
            ps.setInt(2, patient.getId());
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
    public Patient getById(int id, String lang) {
        Connection connection = factoryDAO.getConnection();
        Patient patient = null;
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PATIENT_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                patient = new Patient();
                patient.setId(rs.getInt("pat.id"));
                patient.setName(
                        rs.getString(
                                (lang.equals(Const.RU))? "pat.name_ru": "pat.name"));
                patient.setSurname(
                        rs.getString(
                                (lang.equals(Const.RU))? "pat.surname_ru": "pat.surname"));
//                patient.setName(rs.getString("pat.name"));
//                patient.setSurname(rs.getString("pat.surname"));
                patient.setGender(Gender.valueOf(rs.getString("gender")));
                patient.setYear(rs.getInt("year"));
                patient.setPhone(rs.getString("phone"));
                patient.setUser(factoryDAO.getUserDAO().getById(rs.getInt("pat.user_id"), connection));

                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("doc.id"));
                doctor.setName(
                        rs.getString(
                                (lang.equals(Const.RU))? "doc.name_ru": "doc.name"));
                doctor.setSurname(
                        rs.getString(
                                (lang.equals(Const.RU))? "doc.surname_ru": "doc.surname"));
//                doctor.setName(rs.getString("doc.name"));
//                doctor.setSurname(rs.getString("doc.surname"));
                doctor.setType(DoctorType.valueOf(rs.getString("type")));
                doctor.setExperience(rs.getInt("experience"));
                doctor.setUser(factoryDAO.getUserDAO().getById(rs.getInt("doc.user_id"), connection));
                patient.setDoctor(doctor);
                patient.setRecords(factoryDAO.getCardRecordDAO().getRecordOfOnePatient(patient, connection, lang));
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

    private Patient seUpPatientInfo(ResultSet rs, Connection connection, Doctor doctor, String lang) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getInt("id"));
        patient.setName(
                rs.getString(
                        (lang.equals(Const.RU))? "name_ru": "name"));
        patient.setSurname(
                rs.getString(
                        (lang.equals(Const.RU))? "surname_ru": "surname"));
        patient.setGender(Gender.valueOf(rs.getString("gender")));
        patient.setYear(rs.getInt("year"));
        patient.setPhone(rs.getString("phone"));
        patient.setUser(factoryDAO.getUserDAO().getById(rs.getInt("user_id"), connection));
        patient.setDoctor(
                (doctor!=null)?
                doctor:
                factoryDAO.getDoctorDAO().getById(rs.getInt("doc_id"), lang));
        patient.setRecords(factoryDAO.getCardRecordDAO().getRecordOfOnePatient(patient, connection, lang));
        System.out.println(patient.getId()+" "+patient.getSurname());
        return patient;
    }

    private List<Patient> getPatientsByRequest(String request, String lang){
        System.out.println("get pat by request");
        List<Patient> patients = new ArrayList<>();
        Connection connection = factoryDAO.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request)){
            System.out.println(lang);
            while(rs.next()){

                patients.add(seUpPatientInfo(rs, connection, null, lang));
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
    public Patient changeLanguage(String lang, Patient patient, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            ps.setInt(1, patient.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                patient.setName(
                        rs.getString(
                                (lang.equals(Const.RU)) ? "name_ru" : "name"));
                patient.setSurname(
                        rs.getString(
                                (lang.equals(Const.RU)) ? "surname_ru" : "surname"));

                patient.getDoctor().setName(
                        rs.getString(
                                (lang.equals(Const.RU))? "doc.name_ru": "doc.name"));
                patient.getDoctor().setSurname(
                        rs.getString(
                                (lang.equals(Const.RU))? "doc.surname_ru": "doc.surname"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> getAll(String lang) {
        return  getPatientsByRequest(SELECT_ALL, lang);
    }

    @Override
    public List<Patient> getNonActive(String lang) {
        return getPatientsByRequest(SELECT_NON_REGISTER, lang);
    }

    @Override
    public List<Patient> sort(String request, String lang) {
        System.out.println("in sort");
        return getPatientsByRequest(request, lang);
    }

    @Override
    public List<Patient> getPatientsOfOneDoctor(Doctor doctor, Connection connection, String lang) {
        List<Patient> patients = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PATIENTS_OF_ONE_DOCTOR)){
            ps.setInt(1, doctor.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                patients.add(seUpPatientInfo(rs, connection, doctor, lang));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> getPatientsForDoctorService(Doctor doctor, String lang){
        Connection connection = factoryDAO.getConnection();
        List<Patient> patients = getPatientsOfOneDoctor(doctor, connection, lang);
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }
}
