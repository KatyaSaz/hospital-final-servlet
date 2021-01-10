package ua.sazonova.hospital.dao.mySql;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.dao.DoctorDAO;
import ua.sazonova.hospital.entity.extend.DoctorExtend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDoctorDAO implements DoctorDAO {

    private final String SELECT_DOCTOR_BY_ID="SELECT * FROM `doctors` WHERE id=?";
    private final String SELECT_USER_ID = "SELECT user_id FROM `doctors` WHERE id=?";
    private final String SELECT_ID_BY_USER_ID = "SELECT `id` FROM `doctors` WHERE `user_id`=?";
    private final String SELECT_ALL="SELECT * FROM `doctors`";
    private final String SELECT_ALL_BY_ONE_TYPE ="SELECT * FROM `doctors` WHERE `type`=?";
    private final String SELECT_NON_REGISTER = "SELECT doc.id, doc.name, doc.surname, doc.type, doc.experience, doc.user_id FROM users AS user LEFT JOIN doctors AS doc ON user.id=doc.user_id WHERE user.role='DOCTOR' AND user.is_active=false";
    private final String INSERT_DOCTOR="INSERT INTO `doctors`(`name`, `surname`, `type`, `experience`, `user_id`, `name_ru`, `surname_ru`) VALUES (?,?,?,?,?,?,?)";
    private final String DELETE_DOCTOR="DELETE FROM `doctors` WHERE id=?";

    private MySqlFactoryDAO factoryDAO;

    public MySqlDoctorDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
    }

    private int getIdOfDoctorByUserId(int userId, Connection connection) throws SQLException {
        int doctorId = -1;
        PreparedStatement ps = connection.prepareStatement(SELECT_ID_BY_USER_ID);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            doctorId = rs.getInt("id");
        }
        return doctorId;
    }

    private int createDoctor(DoctorExtend doctor, int userID, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_DOCTOR);
        ps.setString(1, doctor.getName());
        ps.setString(2, doctor.getSurname());
        ps.setString(3, doctor.getType().toString());
        ps.setInt(4, doctor.getExperience());
        ps.setInt(5, userID);
        ps.setString(6, doctor.getName_ru());
        ps.setString(7, doctor.getSurname_ru());
        ps.execute();
        ps.close();
        return getIdOfDoctorByUserId(userID, connection);
    }

    @Override
    public void create(DoctorExtend doctor) {
        Connection connection = factoryDAO.getConnection();
        try {
            connection.setAutoCommit(false);
            int userID = factoryDAO.getUserDAO().create(doctor.getUser(), connection);
            int docID = createDoctor(doctor, userID, connection);
            factoryDAO.getUserDAO().updateMoreInfoId(userID, docID, connection);
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

    private void changeDoctor(int id){
        List<Patient> patients = getById(id, Const.EN).getPatients();
        Doctor defaultDoc = getById(Doctor.DEFAULT_DOCTOR_ID, Const.EN);
        for(Patient pat: patients){
            pat.setDoctor(defaultDoc);
            factoryDAO.getPatientDAO().updateDoctor(pat);
        }
    }

    private void deleteDoctor(int id, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_DOCTOR);
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(int id) {
        Connection connection= factoryDAO.getConnection();
        try {
            connection.setAutoCommit(false);
            changeDoctor(id);
            deleteDoctor(id, connection);
            factoryDAO.getUserDAO().delete(getUserId(id), connection);
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

    private Doctor setUpDoctorInfo(ResultSet rs, Connection connection, String lang) throws SQLException {
//        changeLanguage(String lang, Doctor doctor, Connection connection)
        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt("id"));
        doctor.setName(
                rs.getString(
                (lang.equals(Const.RU))? "name_ru": "name"));
        doctor.setSurname(
                rs.getString(
                        (lang.equals(Const.RU))? "surname_ru": "surname"));
//                rs.getString("surname"));
        doctor.setType(DoctorType.valueOf(rs.getString("type")));
        doctor.setExperience(rs.getInt("experience"));
        doctor.setUser(factoryDAO.getUserDAO().getById(rs.getInt("user_id"), connection));

        doctor.setPatients(factoryDAO.getPatientDAO().getPatientsOfOneDoctor(doctor, connection, lang));
        return doctor;
    }

    @Override
    public Doctor changeLanguage(String lang, Doctor doctor, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_BY_ID)) {
            ps.setInt(1, doctor.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doctor.setName(
                        rs.getString(
                                (lang.equals(Const.RU)) ? "name_ru" : "name"));
                doctor.setSurname(
                        rs.getString(
                                (lang.equals(Const.RU)) ? "surname_ru" : "surname"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctor;
    }

    private Doctor getDoctorByLanguage(int id, String lang){
        Connection connection = factoryDAO.getConnection();
        Doctor doctor = null;
        try(PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_BY_ID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                doctor = setUpDoctorInfo(rs, connection, lang);
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
    public Doctor getById(int id, String lang) {
        return getDoctorByLanguage(id, lang);
    }
//
//    @Override
//    public Doctor getRussianDoctor(int id) {
//        return getDoctorByLanguage(id, Const.RU);
//    }

    private List<Doctor> getDoctorsByRequest(String request, String lang){
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = factoryDAO.getConnection();
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request)){
            while(rs.next()){
                doctors.add(setUpDoctorInfo(rs, connection,lang));
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
    public List<Doctor> getAll(String lang) {
        return getDoctorsByRequest(SELECT_ALL, lang);
    }

    @Override
    public List<Doctor> getNonActive(String lang) {
        return getDoctorsByRequest(SELECT_NON_REGISTER, lang);
    }

    @Override
    public List<Doctor> sort(String request, String lang) {
        return getDoctorsByRequest(request, lang);
    }

    @Override
    public List<Doctor> findBySameType(DoctorType doctorType, String lang) {
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = factoryDAO.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_ONE_TYPE)){
            ps.setString(1, doctorType.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                doctors.add(setUpDoctorInfo(rs, connection, lang));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
