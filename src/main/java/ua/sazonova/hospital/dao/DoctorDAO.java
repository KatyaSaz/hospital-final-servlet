package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.enam.DoctorType;

import java.util.List;

public interface DoctorDAO extends SimpleDAO<Doctor>{
    List<Doctor> findBySameType(DoctorType doctorType);
    Doctor getRussianDoctor(int id);
}
