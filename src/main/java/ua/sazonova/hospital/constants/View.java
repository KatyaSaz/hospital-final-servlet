package ua.sazonova.hospital.constants;

public interface View {
    String PATIENT_VIEW = "WEB-INF/view/patient/info.jsp";

    String DOCTOR_VIEW = "WEB-INF/view/doctor/index.jsp";
    String DOCTOR_HIS_PATIENTS_VIEW = "WEB-INF/view/doctor/showMyPatients.jsp";
    String DOCTOR_ONE_PATIENT_VIEW = "WEB-INF/view/doctor/onePatient.jsp";
    String DOCTOR_WRITE_RECORD_VIEW = "WEB-INF/view/doctor/writeRecord.jsp";

    String ADMIN_VIEW = "WEB-INF/view/admin/index.jsp";
    String ADMIN_DOCTORS_VIEW = "WEB-INF/view/admin/showDoctors.jsp";
    String ADMIN_PATIENTS_VIEW = "WEB-INF/view/admin/showPatients.jsp";
    String ADMIN_NON_REG_VIEW = "WEB-INF/view/admin/showNonReg.jsp";
    String ADMIN_ONE_DOCTOR_VIEW="WEB-INF/view/admin/oneDoctor.jsp";
    String ADMIN_ONE_PATIENT_VIEW="WEB-INF/view/admin/onePatient.jsp";

    String REG_USER_VIEW = "WEB-INF/view/registration/userReg.jsp";
    String REG_DOCTOR_VIEW = "WEB-INF/view/registration/doctorReg.jsp";
    String REG_PATIENT_VIEW= "WEB-INF/view/registration/patientReg.jsp";
    String REG_SUCCESS_VIEW = "WEB-INF/view/registration/success.jsp";

    String LOGIN_FORM_VIEW="WEB-INF/view/login.jsp";
    String LOGIN_ERROR_USER_NOT_FOUND_VIEW="WEB-INF/view/errors/userNotFound.jsp";
    String LOGIN_ERROR_WRONG_PASSWORD_VIEW="WEB-INF/view/errors/passwordWrong.jsp";
}
