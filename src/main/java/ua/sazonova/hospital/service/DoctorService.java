package ua.sazonova.hospital.service;

import ua.sazonova.hospital.dao.DoctorDAO;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.PatientDAO;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;

import java.util.List;

public class DoctorService {

    public static final int MY_SQL = 1;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    FactoryDAO factoryDAO;

    public DoctorService(){
        factoryDAO=FactoryDAO.getInstance(MY_SQL);
        doctorDAO = FactoryDAO.getInstance(MY_SQL).getDoctorDAO();
        patientDAO = FactoryDAO.getInstance(MY_SQL).getPatientDAO();
    }

    public Doctor getDoctorById(String docID){
        return (docID!=null)? doctorDAO.getById(Integer.valueOf(docID)): null;
    }

    public Doctor getRussianDoctor(String docID){
        return (docID!=null)? doctorDAO.getRussianDoctor(Integer.valueOf(docID)): null;
    }

    public List<Patient> getPatientsOfDoctor(Doctor doctor){
        return patientDAO.getPatientsForDoctorService(doctor);
    }
}
