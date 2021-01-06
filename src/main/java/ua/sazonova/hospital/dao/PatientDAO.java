package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;

import java.sql.Connection;
import java.util.List;

public interface PatientDAO extends SimpleDAO<Patient> {
//
//    List<Patient> getPatients(int docID, Connection connection);
//
    List<Patient> getPatientsOfOneDoctor(Doctor doctor, Connection connection);
    void updateDoctor(Patient patient);

}
