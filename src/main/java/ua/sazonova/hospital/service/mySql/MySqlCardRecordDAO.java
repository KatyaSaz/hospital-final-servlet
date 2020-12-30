package ua.sazonova.hospital.service.mySql;

import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.RecordType;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.service.CardRecordDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCardRecordDAO implements CardRecordDAO {
    private final String SELECT_RECORD_BY_ID = "SELECT * FROM `card_records` WHERE id=?";

    private final String SELECT_RECORD_OF_ONE_PATIENT="SELECT * FROM `card_records` WHERE pat_id=?";
    private final String INSERT_USER="";

    private MySqlFactoryDAO factoryDAO;

    public MySqlCardRecordDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
    }

    @Override
    public void create(CardRecord cardRecord) {

    }
//    User user = null;
//    Connection connection = factoryDAO.getConnection();
//        try(
//    PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
//        ps.setString(1, email);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//            user =new User();
//            user.setId(rs.getInt("id"));
//            user.setEmail(rs.getString("email"));
//            user.setPassword(rs.getString("password"));
//            user.setRole(Role.valueOf(rs.getString("role")));
//            user.setActive(rs.getBoolean("is_active"));
//            user.setIdMoreInfo(rs.getInt("more_info_id"));
//        }
//
//    } catch (
//    SQLException exc) {
//        exc.printStackTrace();
//    }
//
//        return user;
    @Override
    public CardRecord getByID(int id) {
        CardRecord cardRecord = null;
        Connection connection = factoryDAO.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_RECORD_BY_ID);){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cardRecord = new CardRecord();
                cardRecord.setId(rs.getInt("id"));
                cardRecord.setRecordType(RecordType.valueOf(rs.getString("record_type")));
                cardRecord.setDescription(rs.getString("description"));
                cardRecord.setPatient(factoryDAO.getPatientDAO().getById(rs.getInt("pat_id")));

                //TODO getPatient fields
            }
            rs.close();
        }catch (SQLException exc){
            exc.printStackTrace();
        }
        return cardRecord;
    }

    private final String SELECT_RECORDS_ONE_PATIENT = "SELECT * FROM `card_records` WHERE pat_id=?";

    @Override
    public List<CardRecord> getRecordOfOnePatient(Patient patient, Connection conn) {
        List<CardRecord> cardRecords = new ArrayList<>();
        Connection connection = (conn!=null)? conn:factoryDAO.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_RECORDS_ONE_PATIENT)){
            ps.setInt(1,patient.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CardRecord cardRecord = new CardRecord();
                cardRecord.setId(rs.getInt("id"));
                cardRecord.setRecordType(RecordType.valueOf(rs.getString("record_type")));
                cardRecord.setDescription(rs.getString("description"));
                cardRecord.setPatient(patient);
                cardRecords.add(cardRecord);
            }
            rs.close();
        }catch (SQLException exc){
            exc.printStackTrace();
        }finally {
            if(conn==null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return cardRecords;
    }
}
