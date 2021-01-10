package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.entity.extend.DoctorExtend;

import java.sql.Connection;
import java.util.List;

public interface DoctorDAO extends SimpleDAO<Doctor>{
    void create(DoctorExtend doctor);
    public List<Doctor> findBySameType(DoctorType doctorType, String lang);
//    Doctor getRussianDoctor(int id);
    Doctor changeLanguage(String lang, Doctor doctor, Connection connection);
}
