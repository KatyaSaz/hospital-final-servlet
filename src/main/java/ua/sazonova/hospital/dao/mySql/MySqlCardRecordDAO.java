package ua.sazonova.hospital.dao.mySql;

import ua.sazonova.hospital.entity.CardRecord;
import ua.sazonova.hospital.entity.Patient;
import ua.sazonova.hospital.entity.enam.RecordType;
import ua.sazonova.hospital.dao.CardRecordDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCardRecordDAO implements CardRecordDAO {
    private final String SELECT_RECORD_BY_ID = "SELECT * FROM `card_records` WHERE id=?";

   // private final String SELECT_RECORD_OF_ONE_PATIENT="SELECT * FROM `card_records` WHERE pat_id=?";
    private final String INSERT_RECORD="INSERT INTO `card_records`(`description`, `record_type`, `pat_id`) VALUES (?,?,?)";
    private final String DELETE_RECORD_OF_ONE_PATIENT = "DELETE FROM `card_records` WHERE pat_id=?";
    private MySqlFactoryDAO factoryDAO;

    public MySqlCardRecordDAO(MySqlFactoryDAO factoryDAO) {
        this.factoryDAO = factoryDAO;
    }

    @Override
    public void create(String description, String type, int patId) {
        Connection connection = factoryDAO.getConnection();
        try(PreparedStatement ps=connection.prepareStatement(INSERT_RECORD);){
            ps.setString(1, description);
            ps.setString(2, type);
            ps.setInt(3, patId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public void deleteRecordsOfOnePatient(int patId, Connection connection) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(DELETE_RECORD_OF_ONE_PATIENT);
        ps.setInt(1, patId);
        ps.execute();
        ps.close();
    }


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
