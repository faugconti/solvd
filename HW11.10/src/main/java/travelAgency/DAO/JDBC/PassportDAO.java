package travelAgency.DAO.JDBC;

import travelAgency.model.Passport;
import travelAgency.util.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PassportDAO extends AbstractDAO<Passport> {

    @Override
    public Connection getConnection(){
        //retrieve jdbc connection
        return ConnectionManager.getConnection();
    }

    @Override
    public Passport mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Passport(
                resultSet.getInt("idPassport"),
                resultSet.getInt("idCustomer"),
                resultSet.getString("passportNumber"),
                resultSet.getString("nationality"),
                resultSet.getDate("issueDate").toLocalDate(),
                resultSet.getDate("expirationDate").toLocalDate()
        );
    }

    @Override
    public Map<String, String> getColumnMappings() {
        return Map.of();
    }

    @Override
    public String getTableName() {
        return "PassportDetails";
    }

    @Override
    public Passport getById(int id) {
        String query = "SELECT * from PassportDetails WHERE idPassport = ?";
        return this.findSingle(query,statement -> {
            try{
                statement.setInt(1,id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });}

    @Override
    public List<Passport> getAll() {

        String query = "SELECT * FROM PassportDetails";
        return this.findAll(query);
    }

    @Override
    public void save(Passport passport) {
        String query = "INSERT INTO PassportDetails (idCustomer, passportNumber, nationality, issueDate, expirationDate) VALUES (?, ?, ?, ?, ?)";
        int generatedId = this.runUpdate(query,statement->{
            try {
                statement.setInt(1, passport.getIdCustomer());
                statement.setString(2, passport.getPassportNumber());
                statement.setString(3, passport.getNationality());
                statement.setDate(4, Date.valueOf(passport.getIssueDate()));
                statement.setDate(5, Date.valueOf(passport.getExpirationDate()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        passport.setId(generatedId);

    }

    @Override
    public void update(Passport passport, String[] params) {

    }

    @Override
    public void remove(Passport passport) {
        String query = "DELETE FROM PassportDetails where idPassport = ?";
        System.out.println(passport.getId());
        runUpdate(query,statement -> {
            try{
                statement.setInt(1,passport.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
