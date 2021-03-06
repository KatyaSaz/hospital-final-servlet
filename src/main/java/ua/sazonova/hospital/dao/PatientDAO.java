package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.extend.PatientExtend;

import java.sql.Connection;
import java.util.List;

public interface PatientDAO extends SimpleDAO<Patient> {
    void create(PatientExtend patientExtend);
    void updateDoctor(Patient patient);
    List<Patient> getPatientsOfOneDoctor(Doctor doctor, Connection connection, String lang);
    List<Patient> getPatientsForDoctorService(Doctor doctor, String lang);
    Patient changeLanguage(String lang, Patient patient, Connection connection);
}
