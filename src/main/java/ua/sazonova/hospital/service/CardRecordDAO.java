package ua.sazonova.hospital.service;

import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Patient;

import java.sql.Connection;
import java.util.List;


public interface CardRecordDAO {
    void create(String description, String type, int patId);
    CardRecord getByID(int id);
    List<CardRecord> getRecordOfOnePatient(Patient patient, Connection connection);
}
