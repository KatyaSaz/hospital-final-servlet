package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.enam.DoctorType;
import ua.sazonova.hospital.entity.extend.DoctorExtend;

import java.util.List;

public interface DoctorDAO extends SimpleDAO<Doctor>{
    void create(DoctorExtend doctor);
    List<Doctor> findBySameType(DoctorType doctorType, String lang);
}
