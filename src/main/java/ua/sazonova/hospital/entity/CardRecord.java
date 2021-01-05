package ua.sazonova.hospital.entity;

import ua.sazonova.hospital.entity.enam.RecordType;

public class CardRecord {
    private int id;
    private RecordType recordType;
    private String description;
    private Patient patient;

    public CardRecord() {
    }

    public CardRecord(int id, RecordType recordType, String description, Patient patient) {
        this.id = id;
        this.recordType = recordType;
        this.description = description;
        this.patient = patient;
    }

    public CardRecord(RecordType recordType, String description, Patient patient) {
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

        public String diagnoseToString(){
        return "Patient name: "+ patient.getName()+" "+ patient.getSurname()+"\n"
                +"Doctor name: "+ patient.getDoctor().getName()+" "+ patient.getDoctor().getSurname()+"\n"
                +recordType+": "+ description;
    }

    public String getFileName(){
        return "diagnose_"+patient.getSurname()+".txt";
    }
}
