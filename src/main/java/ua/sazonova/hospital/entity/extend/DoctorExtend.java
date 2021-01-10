package ua.sazonova.hospital.entity.extend;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.DoctorType;

public class DoctorExtend extends Doctor {

    private String name_ru;
    private String surname_ru;

    public DoctorExtend() {
    }

    public DoctorExtend(String name, String surname, DoctorType type, int experience, User user, String name_ru, String surname_ru) {
        super(name, surname, type, experience, user);
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
