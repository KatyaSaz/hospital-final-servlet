package ua.sazonova.hospital.entity.extend;

import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Gender;

public class PatientExtend extends Patient {

    private String name_ru;
    private String surname_ru;

    public PatientExtend() {
    }

    public PatientExtend(String name, String surname, Gender gender, int year, String phone, User user, String name_ru, String surname_ru) {
        super(name, surname, gender, year, phone, user);
        this.name_ru = name_ru;
        this.surname_ru = surname_ru;
    }

    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    public String getSurname_ru() {
        return surname_ru;
    }

    public void setSurname_ru(String surname_ru) {
        this.surname_ru = surname_ru;
    }
}
