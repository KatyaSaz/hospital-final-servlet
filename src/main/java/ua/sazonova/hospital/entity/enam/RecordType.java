package ua.sazonova.hospital.entity.enam;

public enum RecordType {
    Direction("Направление"),
    Execution("Процедура"),
    Diagnosis("Диагноз");

    private String type_ru;

    RecordType(String type_ru){
        this.type_ru = type_ru;
    }

    public String getType_ru() {
        return type_ru;
    }
}
