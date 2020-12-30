//package ua.sazonova.hospital;
//
//import ua.sazonova.hospital.entity.CardRecord;
//import ua.sazonova.hospital.entity.Doctor;
//import ua.sazonova.hospital.entity.Patient;
//import ua.sazonova.hospital.entity.User;
//import ua.sazonova.hospital.service.*;
//
//import java.util.List;
//
//public class Application {
//    public final static int MY_SQL = 1;
//
//    public static void main(String[] args) {
//        FactoryDAO factoryDAO = FactoryDAO.getInstance(MY_SQL);
////        UserDAO userDAO = factoryDAO.getUserDAO();
////        User userFind = userDAO.findByEmail("patient@gmail.com");
////        User userFind = userDAO.getById(3);
////        CardRecordDAO cardRecordDAO = factoryDAO.getCardRecordDAO();
////        CardRecord cardRecord = cardRecordDAO.getByID(3);
////        System.out.println(cardRecord);
//
////       DoctorDAO doctorDAO = factoryDAO.getDoctorDAO();
////        Doctor doctor = doctorDAO.getById(101);
////        List<Patient> patients = doctor.getPatients();
////        System.out.println(doctor);
////        for (Patient p:patients){
////            System.out.println(p);
////           for(CardRecord cr:p.getRecords()){
////               System.out.println(p.getId()+" : "+cr);
////           }
////        }
//
//          PatientDAO patientDAO = factoryDAO.getPatientDAO();
//       Patient patient = patientDAO.getById(201);
//        System.out.println(patient);
//
////
////        List<Patient> patient = patientDAO.getPatientsOfOneDoctor(doctor.getId());
////        for (Patient p:patient){
////            System.out.println(p);
////        }
//
//
//        //System.out.println(userFind);
//
//    }
//}
