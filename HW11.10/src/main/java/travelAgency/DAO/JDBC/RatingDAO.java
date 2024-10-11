package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Rating;

import java.util.List;

public class RatingDAO implements DAO<Rating> {

    @Override
    public Rating getById(int id) {
        return null;
    }

    @Override
    public List<Rating> getAll() {
        return List.of();
    }

    @Override
    public void save(Rating rating) {

    }

    @Override
    public void update(Rating rating, String[] params) {

    }

    @Override
    public void remove(Rating rating) {

    }
}
