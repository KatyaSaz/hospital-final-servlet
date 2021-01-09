package ua.sazonova.hospital.entity.enam;

public enum DoctorType {

    dermatologist("дерматолог"),
    pediatrician ("педиатр"),
    surgeon("хирург"),
    optometrist("окулист");

    private String name_ru;

    DoctorType(String name_ru) {
        this.name_ru = name_ru;
    }

    public String getName_ru() {
        return name_ru;
    }
}
