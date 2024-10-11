package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Destination;

import java.util.List;

public class DestinationDAO implements DAO<Destination>
{
    @Override
    public Destination getById(int id) {
        return null;
    }

    @Override
    public List<Destination> getAll() {
        return List.of();
    }

    @Override
    public void save(Destination destination) {

    }

    @Override
    public void update(Destination destination, String[] params) {

    }

    @Override
    public void remove(Destination destination) {

    }
}
