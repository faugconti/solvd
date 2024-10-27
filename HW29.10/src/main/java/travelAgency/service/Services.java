package travelAgency.service;

import java.util.List;

public interface Services<T> {
    void findById();
    void findAll();
    void add();
    void update();
    void remove();
    Class<T> getType();
}
