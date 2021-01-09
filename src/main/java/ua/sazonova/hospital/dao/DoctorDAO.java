package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.enam.DoctorType;

import java.sql.Connection;
import java.util.List;

public interface DoctorDAO extends SimpleDAO<Doctor>{

    public List<Doctor> findBySameType(DoctorType doctorType, String lang);
//    Doctor getRussianDoctor(int id);
    Doctor changeLanguage(String lang, Doctor doctor, Connection connection);
}
