package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Patient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface CardRecordDAO {
    void create(String description, String type, int patId);
    void deleteRecordsOfOnePatient(int patId, Connection connection) throws SQLException;
    CardRecord getByID(int id);
    List<CardRecord> getRecordOfOnePatient(Patient patient, Connection connection);
}
