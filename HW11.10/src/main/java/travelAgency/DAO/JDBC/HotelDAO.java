package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;

import java.util.List;

public class HotelDAO implements DAO<HotelDAO> {
    @Override
    public HotelDAO getById(int id) {
        return null;
    }

    @Override
    public List<HotelDAO> getAll() {
        return List.of();
    }

    @Override
    public void save(HotelDAO hotel) {

    }

    @Override
    public void update(HotelDAO hotel, String[] params) {

    }

    @Override
    public void remove(HotelDAO hotel) {

    }
}
