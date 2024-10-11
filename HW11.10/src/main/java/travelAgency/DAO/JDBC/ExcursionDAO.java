package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Excursion;

import java.util.List;

public class ExcursionDAO implements DAO<Excursion> {
    @Override
    public Excursion getById(int id) {
        return null;
    }

    @Override
    public List<Excursion> getAll() {
        return List.of();
    }

    @Override
    public void save(Excursion excursion) {

    }

    @Override
    public void update(Excursion excursion, String[] params) {

    }

    @Override
    public void remove(Excursion excursion) {

    }
}
