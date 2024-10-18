package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.AbstractDAO;
import travelAgency.DAO.JDBC.EntityDAO;
import travelAgency.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService<T> implements Services<T> {

    public final DAO<T> dao;
    private final Class<T> type;

    public DatabaseService(Class<T> entityClass){
        this.dao = new EntityDAO<>(entityClass);
        this.type = entityClass;
    }

    @Override
    public T findById(int id) {
        return this.dao.getById(id);
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
        System.out.println("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if(entity == null){
            System.out.println("No row found for that ID.");
            return;
        }
        System.out.println("Object Found: \n"+entity);
        T newEntity = (T) ReflectionUtils.askForEntityAttributes(this.type,excludeFields); //create a new object
        newEntity = (T) ReflectionUtils.setID(newEntity,id); // object with modified attributes.

        List<String> attributes = new ArrayList<>();
        for (Field field : newEntity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
            try {
                Object currentValue = field.get(newEntity);
                attributes.add(currentValue.toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        String[] attributesArray = attributes.toArray(new String[0]);
        System.out.println(attributes);
        this.dao.update(entity,attributesArray);
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
