package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Payment;

import java.util.List;

public class PaymentDAO implements DAO<Payment> {
    @Override
    public Payment getById(int id) {
        return null;
    }

    @Override
    public List<Payment> getAll() {
        return List.of();
    }

    @Override
    public void save(Payment payment) {

    }

    @Override
    public void update(Payment payment, String[] params) {

    }

    @Override
    public void remove(Payment payment) {

    }
}
