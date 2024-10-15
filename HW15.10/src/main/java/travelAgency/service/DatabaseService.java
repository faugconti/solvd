package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.EntityDAO;

import java.util.List;

public class DatabaseService<T> implements Services<T> {

    public final DAO<T> dao;

    public DatabaseService(Class<T> entityClass){
        this.dao = new EntityDAO<>(entityClass);
    }

    @Override
    public T findById(int id) {
        return this.dao.getById(id);
    }

    @Override
    public List<T> findAll() {
        return this.dao.getAll();
    }

    @Override
    public void save(T t) {
        this.dao.save((T) t);
    }


    @Override
    public void update(T entity, String[] params) {
        this.dao.update((T) entity,params);
    }

    @Override
    public void remove(T entity) {
        this.dao.remove((T) entity);
    }
}
