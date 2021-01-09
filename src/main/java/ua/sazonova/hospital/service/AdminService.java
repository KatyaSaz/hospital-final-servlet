package ua.sazonova.hospital.service;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.dao.DoctorDAO;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.PatientDAO;
import ua.sazonova.hospital.dao.UserDAO;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.enam.DoctorType;

import java.util.List;

public class AdminService {

    private static final String SELECT_ALL = "SELECT * FROM ";
    private static final String WHERE_ID = " WHERE doc_id=";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String SPACE =" ";

    public static final int MY_SQL = 1;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private UserDAO userDAO;

    public AdminService() {
        doctorDAO = FactoryDAO.getInstance(MY_SQL).getDoctorDAO();
        patientDAO = FactoryDAO.getInstance(MY_SQL).getPatientDAO();
        userDAO = FactoryDAO.getInstance(MY_SQL).getUserDAO();
    }

    public void createDoctor(Doctor doctor) {
        doctorDAO.create(doctor);
    }

    public void createPatient(Patient patient) {
        patientDAO.create(patient);
    }

    public void deletePatient(String patId) {
        patientDAO.delete(Integer.valueOf(patId));
    }

    public void deleteDoctor(String docId) {
        doctorDAO.delete(Integer.valueOf(docId));
    }

    public void makeUserActiveForDoctor(String docId) {
        userDAO.makeUserActive(doctorDAO.getUserId(Integer.valueOf(docId)));
    }

    public void makeUserActiveForPatient(String patId) {
        userDAO.makeUserActive(patientDAO.getUserId(Integer.valueOf(patId)));
    }

    public void changeDoctorForPatient(String patId, String newDocId, String lang) {
        Patient patient = patientDAO.getById(Integer.valueOf(patId),lang);
        patient.setDoctor(doctorDAO.getById(Integer.valueOf(newDocId), lang));
        patientDAO.updateDoctor(patient);
    }

    public List<Doctor> getAllDoctors(String lang) {
        return doctorDAO.getAll(lang);
    }

    public List<Patient> getAllPatients(String lang) {
        return patientDAO.getAll(lang);
    }

    public List<Doctor> getNonActiveDoctors(String lang) {
        return doctorDAO.getNonActive(lang);
    }

    public List<Patient> getNonActivePatients(String lang) {
        return patientDAO.getNonActive(lang);
    }

    public List<Doctor> getAllDoctorsBySameType(DoctorType doctorType, String lang) {
        return doctorDAO.findBySameType(doctorType, lang);
    }

    public List<Doctor> sortDoctors(String field, String direction, String lang) {
        return doctorDAO.sort(
                makeUpSortSelect(Const.DOCTORS, field, direction, null), lang);
    }

    public List<Patient> sortPatients(String field, String direction, String lang) {
        System.out.println(makeUpSortSelect(Const.PATIENTS, field, direction, null));
        return patientDAO.sort(
                makeUpSortSelect(Const.PATIENTS, field, direction, null), lang);
    }

    public List<Patient> sortPatientsOfOneDoctor(String doc_id, String field, String direction, String lang) {
        return patientDAO.sort(
                makeUpSortSelect(Const.PATIENTS, field, direction, doc_id), lang);
    }



    private String makeUpSortSelect(String tableName, String field, String direction, String doc_id) {
        return (doc_id != null) ?
                (SELECT_ALL + tableName + WHERE_ID + doc_id + ORDER_BY + field + SPACE + direction) :
                (SELECT_ALL + tableName + ORDER_BY + field + SPACE + direction);
    }
}
