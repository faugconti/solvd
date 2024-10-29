package travelAgency.service;

import travelAgency.DAO.DAO;

public abstract class AbstractService<T> implements Services<T>{
    DAO<T> daoStrategy;
    Class<T> type;

    public AbstractService(Class<T> entityClass) {
        this.type = entityClass;
    }

    public void setDAOStrategy(DAO<?> daoStrategy) {
        this.daoStrategy = (DAO<T>) daoStrategy;
    }
}
