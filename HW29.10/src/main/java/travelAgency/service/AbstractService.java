package travelAgency.service;

import travelAgency.DAO.DAO;

public abstract class AbstractService<T> implements Services<T>{
    DAO<T> dao; // = DAOStrategy
    Class<T> type;

    public AbstractService(Class<T> entityClass) {
        this.type = entityClass;
    }

    public void setDAOStrategy(DAO<?> daoStrategy) {
        this.dao = (DAO<T>) daoStrategy;
    }
}
