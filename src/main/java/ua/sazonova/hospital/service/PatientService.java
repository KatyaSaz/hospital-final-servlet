package ua.sazonova.hospital.service;

import ua.sazonova.hospital.dao.CardRecordDAO;
import ua.sazonova.hospital.dao.FactoryDAO;
import ua.sazonova.hospital.dao.PatientDAO;
import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Patient;

public class PatientService {

    public static final int MY_SQL = 1;
    private PatientDAO patientDAO;
    private CardRecordDAO cardRecordDAO;

    public PatientService(){
        patientDAO = FactoryDAO.getInstance(MY_SQL).getPatientDAO();
        cardRecordDAO = FactoryDAO.getInstance(MY_SQL).getCardRecordDAO();
    }

    public Patient getPatientById(String patientId){
        return (patientId!=null)? patientDAO.getById(Integer.valueOf(patientId)): null;
    }

    public CardRecord getIdOfCardRecordToSave(String saveCardId){
        return  (saveCardId!=null)? cardRecordDAO.getByID(Integer.valueOf(saveCardId)):null;
    }

    public void createCardRecord(String description, String type, int patId){
        cardRecordDAO.create(description,type,patId);
    }
}
