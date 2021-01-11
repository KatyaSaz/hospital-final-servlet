package ua.sazonova.hospital.entity;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.entity.enam.RecordType;

public class CardRecord {
    private int id;
    private RecordType recordType;
    private String description;
    private Patient patient;

    public CardRecord() {}

    /**
     * All fields constructor
     *
     * @param id          - id of card record
     * @param recordType  - type of record (enumeration RecordType)
     * @param description - the recording itself
     * @param patient     - patient in whose card this entry was made
     */
    public CardRecord(int id, RecordType recordType, String description, Patient patient) {
        this.id = id;
        this.recordType = recordType;
        this.description = description;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "CardRecord{" +
                "id=" + id +
                ", recordType=" + recordType +
                ", description='" + description + '\'' +
                ", patient=" + patient +
                '}';
    }

    public String diagnoseToString() {
        return Const.PATIENT_NAME + patient.getName() + Const.SPACE
                + patient.getSurname() + Const.NEW_LINE
                + Const.DOCTOR_NAME + patient.getDoctor().getName() + Const.SPACE
                + patient.getDoctor().getSurname() + Const.NEW_LINE
                + recordType + Const.COLON + description;
    }

    public String getFileName() {
        return Const.DIAGNOSE + patient.getSurname() + Const.TXT_FORMAT;
    }
}
