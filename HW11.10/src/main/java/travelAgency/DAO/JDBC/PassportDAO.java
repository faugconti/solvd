package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Passport;

import java.util.List;

public class PassportDAO implements DAO<Passport> {


    @Override
    public Passport getById(int id) {
    return null;
    }

    @Override
    public List<Passport> getAll() {
        return List.of();
    }

    @Override
    public void save(Passport passport) {

    }

    @Override
    public void update(Passport passport, String[] params) {

    }

    @Override
    public void remove(Passport passport) {

    }
}
