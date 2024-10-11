package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Transportation;

import java.util.List;

public class TransportationDAO implements DAO<Transportation> {
    @Override
    public Transportation getById(int id) {
        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return List.of();
    }

    @Override
    public void save(Transportation transportation) {

    }

    @Override
    public void update(Transportation transportation, String[] params) {

    }

    @Override
    public void remove(Transportation transportation) {

    }
}
