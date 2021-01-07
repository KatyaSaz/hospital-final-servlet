package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;

import java.sql.Connection;
import java.util.List;

public interface PatientDAO extends SimpleDAO<Patient> {
//
//    List<Patient> getPatients(int docID, Connection connection);
//
//List<Patient> sortPatientsOfOneDoctor(Doctor doctor, String request);
    List<Patient> getPatientsOfOneDoctor(Doctor doctor, Connection conn);
//    List<Patient> sortPatientsOfOneDoctor(int id, String request);
    void updateDoctor(Patient patient);
    List<Patient> sortPatients(String request);
    List<Patient> getPatientsForDoctorService(Doctor doctor);

}
