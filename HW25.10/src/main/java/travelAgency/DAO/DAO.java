package travelAgency.DAO;

import java.util.List;

//CRUD operations
public interface DAO <T>{
    T getById(int id);
    List<T> getAll();
    void save(T t);
    void update(T t,String[] params);
    void remove(T t);

    T findById(Long id);

    List<T> findAll();
}
