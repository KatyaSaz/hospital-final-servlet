package ua.sazonova.hospital.entity;

import ua.sazonova.hospital.entity.enam.DoctorType;

import java.util.List;

public class Doctor {
    public static final int DEFAULT_DOCTOR_ID = 103;

    private int id;
    private String name;
    private String surname;
    private DoctorType type;
    private int experience;
    private List<Patient> patients;
    private User user;

    public Doctor() {
    }

    public Doctor(int id, String name, String surname, DoctorType type, int experience, List<Patient> patients, User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.experience = experience;
        this.patients = patients;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public DoctorType getType() {
        return type;
    }

    public void setType(DoctorType type) {
        this.type = type;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type=" + type +
                ", experience=" + experience +
//                ", patients=" + patients +
                ", user=" + user +
                '}';
    }
}
