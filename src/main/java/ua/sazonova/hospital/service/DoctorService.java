package ua.sazonova.hospital.service;

import ua.sazonova.hospital.dao.DoctorDAO;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.entity.Doctor;

public class DoctorService {

    public static final int MY_SQL = 1;
    private DoctorDAO doctorDAO;

    public DoctorService(){
        doctorDAO = FactoryDAO.getInstance(MY_SQL).getDoctorDAO();
    }

    public Doctor getDoctorById(String docID){
        return (docID!=null)? doctorDAO.getById(Integer.valueOf(docID)): null;
    }

}
