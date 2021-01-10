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

    public Patient getPatientById(String patientId, String lang){
        return (patientId!=null)? patientDAO.getById(Integer.valueOf(patientId), lang): null;
    }

    public CardRecord getIdOfCardRecordToSave(String saveCardId, String lang){
        return  (saveCardId!=null)? cardRecordDAO.getByID(Integer.valueOf(saveCardId), lang):null;
    }

    public void createCardRecord(String description_en, String description_ru, String type, int patId){
        cardRecordDAO.create(description_en, description_ru, type, patId);
    }
}
