package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.EntityDAO;
import travelAgency.util.ReflectionUtils;

import java.util.List;

public class DatabaseService<T> implements Services<T> {

    public final DAO<T> dao;
    private final Class<T> type;

    public DatabaseService(Class<T> entityClass){
        this.dao = new EntityDAO<>(entityClass);
        this.type = entityClass;
    }


    @Override
    public void findById() {
        System.out.println("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if(entity == null){
            System.out.println(this.type.getSimpleName()+" not found.");
            return;
        }else{
            System.out.println(entity);
        }

    }

    @Override
    public void findAll() {
        List<T> rows = this.dao.getAll();
        System.out.println(rows);
    }

    @Override
    public void add() {
        T entity = (T) ReflectionUtils.askForEntityAttributes(this.type,null);
        this.dao.save(entity);
    }

    @Override
    public void update() {;
        List<String> excludeFields = List.of("id"+this.getType().getSimpleName());
        System.out.print("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if(entity == null){
            System.out.println("No row found for that ID.");
            return;
        }
        System.out.println("Object Found: \n"+entity);
        T newEntity = (T) ReflectionUtils.askForEntityAttributes(this.type,excludeFields); //create a new object
        newEntity = (T) ReflectionUtils.setID(newEntity,id); // object with modified attributes.


        this.dao.update(entity,ReflectionUtils.extractEntityAttributes(newEntity,entity));
    }

    @Override
    public void remove() {
        System.out.println("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        this.dao.remove((T) entity);
    }

    @Override
    public Class<T> getType() {
        return this.type;
    }
}
