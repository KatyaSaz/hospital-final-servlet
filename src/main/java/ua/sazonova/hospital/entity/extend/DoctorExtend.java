package ua.sazonova.hospital.entity.extend;

import ua.sazonova.hospital.entity.Doctor;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.DoctorType;

/**
 * A special class for forming a doctor with fields not only
 * in english, but also in russian (used in registration part).
 */
public class DoctorExtend extends Doctor {

    private String name_ru;
    private String surname_ru;

    public DoctorExtend() {
    }

    /**
     * Constructor for patient forming a doctor
     * with english and russian name fields
     *
     * @param name       - name in english
     * @param surname    - surname in english
     * @param type       - type of doctor (enumeration DoctorType)
     * @param experience - work experience in years
     * @param user       - contains more information about patient
     * @param name_ru    - name in russian
     * @param surname_ru - surname in russian
     */
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
