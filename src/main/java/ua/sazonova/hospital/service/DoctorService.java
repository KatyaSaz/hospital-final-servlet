package ua.sazonova.hospital.service;

import ua.sazonova.hospital.constants.Sort;
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

    public List<Patient> getPatientsOfDoctor(Doctor doctor){
        return patientDAO.getPatientsForDoctorService(doctor);
    }

    public List<Patient> sortPatients(String doc_id, String field, String direction){
        return patientDAO.sortPatients(makeUpSortSelect(Sort.TABLE_PATIENTS, field, direction, doc_id));
    }


    private String makeUpSortSelect(String tableName, String field, String direction, String doc_id){
        return "SELECT * FROM `"+tableName+"` WHERE doc_id="+doc_id+" ORDER BY `"+field+"` "+direction;
    }
}
