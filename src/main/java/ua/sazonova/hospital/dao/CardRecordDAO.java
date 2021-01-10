package ua.sazonova.hospital.dao;

import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Patient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface CardRecordDAO {
    void create(String description_en, String description_ru, String type, int patId);
    void deleteRecordsOfOnePatient(int patId, Connection connection) throws SQLException;
    CardRecord getByID(int id, String lang);
    List<CardRecord> getRecordOfOnePatient(Patient patient, Connection connection, String lang);
}
