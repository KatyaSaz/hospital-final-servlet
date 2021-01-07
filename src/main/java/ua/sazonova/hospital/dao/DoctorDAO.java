package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.DoctorType;

import java.util.List;

public interface DoctorDAO extends SimpleDAO<Doctor>{

//    void create(Doctor doctor, User user);
    List<Doctor> findBySameType(DoctorType doctorType);

}
