package ua.sazonova.hospital.service;

import ua.sazonova.hospital.constants.Sort;
import ua.sazonova.hospital.dao.DoctorDAO;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.PatientDAO;
import ua.sazonova.hospital.dao.UserDAO;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.enam.DoctorType;

import java.util.List;

public class AdminService {

    public static final int MY_SQL = 1;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private UserDAO userDAO;

    public AdminService(){
        doctorDAO = FactoryDAO.getInstance(MY_SQL).getDoctorDAO();
        patientDAO = FactoryDAO.getInstance(MY_SQL).getPatientDAO();
        userDAO = FactoryDAO.getInstance(MY_SQL).getUserDAO();
    }

    public void createDoctor(Doctor doctor){
        doctorDAO.create(doctor);
    }

    public void createPatient(Patient patient){
        patientDAO.create(patient);
    }

    public void deletePatient(String patId){
        patientDAO.delete(Integer.valueOf(patId));
    }

    public void deleteDoctor(String docId){
        doctorDAO.delete(Integer.valueOf(docId));
    }

    public void makeUserActiveForDoctor(String docId){
        userDAO.makeUserActive(doctorDAO.getUserId(Integer.valueOf(docId)));
    }

    public void makeUserActiveForPatient(String patId){
        userDAO.makeUserActive(patientDAO.getUserId(Integer.valueOf(patId)));
    }

    public void changeDoctorForPatient(String patId, String newDocId){
        Patient patient = patientDAO.getById(Integer.valueOf(patId));
        patient.setDoctor(doctorDAO.getById(Integer.valueOf(newDocId)));
        patientDAO.updateDoctor(patient);
    }

    public List<Doctor> getAllDoctors(){
        return doctorDAO.getAll();
    }

    public List<Patient> getAllPatients(){
        return patientDAO.getAll();
    }

    public List<Doctor> getNonActiveDoctors(){
        return doctorDAO.getNonActive();
    }

    public List<Patient> getNonActivePatients(){
        return patientDAO.getNonActive();
    }

    public List<Doctor> getAllDoctorsBySameType(DoctorType doctorType){
        return  doctorDAO.findBySameType(doctorType);
    }


    public List<Doctor> sortDoctors(String field, String direction){
        return doctorDAO.sortDoctors(
                makeUpSortSelect(Sort.TABLE_DOCTORS, field, direction));
    }

    private String makeUpSortSelect(String tableName, String field, String direction){
        return "SELECT * FROM `"+tableName+"` ORDER BY `"+field+"` "+direction;
    }
}
