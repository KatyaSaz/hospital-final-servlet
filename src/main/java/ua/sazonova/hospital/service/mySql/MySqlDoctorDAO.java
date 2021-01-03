package ua.sazonova.hospital.service.mySql;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.entity.enam.Gender;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.service.DoctorDAO;
import ua.sazonova.hospital.service.PatientDAO;
import ua.sazonova.hospital.service.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDoctorDAO implements DoctorDAO {

   // private final String SELECT_DOCTOR_BY_ID="SELECT * FROM `doctors` WHERE id=?";
    private final String SELECT_DOCTOR = "SELECT * FROM doctors AS doc LEFT JOIN patients AS pat ON doc.id=pat.doc_id WHERE doc.id=?";
    private final String SELECT_ALL="SELECT * FROM `doctors`";

    private MySqlFactoryDAO factoryDAO;
    private PatientDAO patientDAO;
    private UserDAO userDAO;

    public MySqlDoctorDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
//        patientDAO = factoryDAO.getPatientDAO();
//        userDAO = factoryDAO.getUserDAO();
    }

    @Override
    public void create(Doctor object) {

    }

    @Override
    public void delete(int id) {

    }

//    private User getUser(int patId){
//        User user = null;
//
//        return user;
//    }




//    public Doctor getDoctor(int docID, Connection connection){
//
//        return doctor;
//    }

    @Override
    public Doctor getById(int id) {
        Connection connection = factoryDAO.getConnection();
        Doctor doctor = null;
        List<Patient> patients = new ArrayList<Patient>();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                doctor = new Doctor();
                doctor.setId(rs.getInt("doc.id"));
                doctor.setName(rs.getString("doc.name"));
                doctor.setSurname(rs.getString("doc.surname"));
                doctor.setType(DoctorType.valueOf(rs.getString("type")));
                doctor.setExperience(rs.getInt("experience"));
                doctor.setUser(factoryDAO.getUserDAO().getById(rs.getInt("doc.user_id"), connection));
               // System.out.println(doctor);

                while(rs.next()){
                    Patient patient =new Patient();
                    patient.setId(rs.getInt("pat.id"));
                    patient.setName(rs.getString("pat.name"));
                    patient.setSurname(rs.getString("pat.surname"));
                    patient.setGender(Gender.valueOf(rs.getString("gender")));
                    patient.setYear(rs.getInt("year"));
                    patient.setPhone(rs.getString("phone"));
                    patient.setUser(factoryDAO.getUserDAO().getById(rs.getInt("pat.user_id"), connection));
                    //patient.setUser(userDAO.getById(rs.getInt("user_id"), connection));
                    patient.setDoctor(doctor);
                    patient.setRecords(factoryDAO.getCardRecordDAO().getRecordOfOnePatient(patient, connection));
                    patients.add(patient);
                }
                doctor.setPatients(patients);
                // doctor.setUser(userDAO.getById(rs.getInt("user_id"), connection));

                // doctor.setPatients(patientDAO.getPatients(doctor.getId(), connection));
                //doctor.setPatients(factoryDAO.getPatientDAO().getPatients(doctor.getId(), connection));
                //doctor.setPatients(factoryDAO.getPatientDAO().getPatientsOfOneDoctor(doctor.getId()));
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




        return doctor;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = factoryDAO.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL)){
            while(rs.next()){
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setName(rs.getString("name"));
                doctor.setSurname(rs.getString("surname"));
                doctor.setType(DoctorType.valueOf(rs.getString("type")));
                doctor.setExperience(rs.getInt("experience"));
                doctor.setUser(factoryDAO.getUserDAO().getById(rs.getInt("user_id"), connection));
                doctor.setPatients(factoryDAO.getPatientDAO().getPatientsOfOneDoctor(doctor, connection));
                doctors.add(doctor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public List<Doctor> getNonActive() {
        return null;
    }
}
