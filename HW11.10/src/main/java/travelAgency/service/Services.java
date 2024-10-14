package travelAgency.service;

import java.util.List;

public interface Services<T> {
    T findById(int id);
    List<T> findAll();
    void save(T t);
    void update(T t,String[] params);
    void remove(T t);
}
