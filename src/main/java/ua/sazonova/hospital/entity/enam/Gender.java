package ua.sazonova.hospital.entity.enam;

public enum Gender {
    male("мужской"),
    female("женский");

    private String gender_ru;

    Gender(String gender_ru){
        this.gender_ru = gender_ru;
    }

    public String getGender_ru() {
        return gender_ru;
    }
}
