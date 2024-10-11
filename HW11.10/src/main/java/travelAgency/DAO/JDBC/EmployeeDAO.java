package travelAgency.DAO.JDBC;

import travelAgency.DAO.DAO;
import travelAgency.model.Employee;

import java.util.List;

public class EmployeeDAO implements DAO<Employee> {

    @Override
    public Employee getById(int id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return List.of();
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void update(Employee employee, String[] params) {

    }

    @Override
    public void remove(Employee employee) {

    }
}
