package ua.sazonova.hospital.entity;

import ua.sazonova.hospital.entity.enam.Gender;

import java.util.List;

public class Patient {

    private int id;
    private String name;
    private String surname;
    private Gender gender;
    private int year;
    private String phone;
    private Doctor doctor;
    private User user;
    private List<CardRecord> records;


    public Patient() {

    }

    public Patient(int id, String name, String surname, Gender gender, int year, String phone, Doctor doctor, User user, List<CardRecord> records) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.year = year;
        this.phone = phone;
        this.doctor = doctor;
        this.user = user;
        this.records = records;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<CardRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CardRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", year=" + year +
                ", phone='" + phone + '\'' +
                ", doctor=" + doctor +
                ", user=" + user +
//                ", records=" + records +
                '}';
    }
}
