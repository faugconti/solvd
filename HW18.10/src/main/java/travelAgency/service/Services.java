package travelAgency.service;

import java.util.List;

public interface Services<T> {
    T findById(int id);
    void findAll();
    void add();
    void update();
    void remove();
    Class<T> getType();
}
