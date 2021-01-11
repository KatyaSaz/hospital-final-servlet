package ua.sazonova.hospital.constants;

public interface Const {
    int DEFAULT_DOCTOR_ID = 103;

    String SESSION_LOCALE = "sessionLocale";
    String LANG = "lang";
    String RU = "ru";
    String EN = "en";

    String USER = "user";
    String USER_REG = "user_reg";
    String DOCTOR = "doctor";
    String DOCTORS = "doctors";
    String PATIENT = "patient";
    String PATIENTS = "patients";

    String PATIENT_ID = "patId";
    String DOCTOR_ID = "docId";

    String REG_DOCTOR_ID = "forRegDocId";
    String REG_PATIENT_ID = "forRegPatId";

    String DELETE_PATIENT_ID = "deletePatId";
    String DELETE_DOCTOR_ID = "deleteDocId";

    String NEW_DOCTOR_ID = "newDocId";

    String SORT_FIELD = "sortField";
    String SORT_DIRECTION = "sortDirection";
    String FIELD_SAVED_VALUE = "fieldS";
    String DIRECTION_SAVED_VALUE = "directS";

    String SEARCH_TYPE_DOCTOR = "searchType";
    String SEARCH_SAVED_VALUE = "typeDoc";

    String CARD_DESCRIPTION_EN = "description_en";
    String CARD_DESCRIPTION_RU = "description_ru";
    String CARD_TYPE = "type";
    String DOWNLOAD_CARD_ID = "downloadCardId";

    String EMAIL = "email";
    String PASSWORD = "password";
    String ROLE = "role";
    String LOG_OUT = "LogOut";

    String NAME_EN = "name_en";
    String SURNAME_EN = "surname_en";
    String NAME_RU = "name_ru";
    String SURNAME_RU = "surname_ru";
    String DOC_TYPE = "type";
    String DOC_EXPERIENCE = "experience";

    String PAT_GENDER = "gender";
    String PAT_YEAR = "year";
    String PAT_PHONE = "phone";

    String UTF_8 = "UTF-8";
    String TEXT_HTML = "text/html";

    String PATIENT_NAME = "Patient name: ";
    String DOCTOR_NAME = "Doctor name: ";
    String SPACE = " ";
    String NEW_LINE = "\n";
    String COLON = ": ";
    String DIAGNOSE = "diagnose_";
    String TXT_FORMAT = ".txt";

    String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_PATH_DB_NAME = "jdbc:mysql://localhost:3306/hospital?";
    String DB_NAME_PASSWORD = "user=root&password=";
    String DB_ENCODING_SETTINGS = "&useEncoding=true&characterEncoding=UTF-8";

    String DB_SQL_EXCEPTION = "SQLException: ";
    String DB_SQL_STATE = "SQLState: ";
    String DB_VENDOR_ERROR = "VendorError: ";
}
