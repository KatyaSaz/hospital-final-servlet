package ua.sazonova.hospital.service;

import ua.sazonova.hospital.dao.DoctorDAO;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.PatientDAO;
import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;

public class DoctorService {
    public static final int MY_SQL = 1;
    private FactoryDAO factoryDAO;
    private DoctorDAO doctorDAO;

    public DoctorService(){
        factoryDAO = FactoryDAO.getInstance(MY_SQL);
        doctorDAO = factoryDAO.getDoctorDAO();
    }

    public Doctor getDoctorById(String docID){
        return (docID!=null)? doctorDAO.getById(Integer.valueOf(docID)): null;
    }

}
