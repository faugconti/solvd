package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.EntityDAO;
import travelAgency.util.JSONUtils;
import travelAgency.util.ReflectionUtils;
import travelAgency.util.enums.Entities;

import java.io.IOException;

import java.util.List;

public class JSONService<T> implements Services<T> {

    public final DAO<T> dao;
    private final Class<T> type;

    public JSONService(Class<T> entityClass){
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
        }
        try {
            JSONUtils.marshallSingleEntity(this.type,entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll() {
        List<T> entities = dao.getAll();
        try {
            JSONUtils.marshallListEntity(Entities.getPluralClassForEntity(this.type),entities);
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add() {
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");
        try {
            this.dao.save((T) JSONUtils.unmarshallSingleEntity(this.type,inputFile));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update() {
        System.out.print("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if (entity == null) {
            System.out.println("No row found for that ID.");
            return;
        }
        // read from JSON
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");
        T newEntity;
        try {
            newEntity = ((T) JSONUtils.unmarshallSingleEntity(this.type, inputFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.dao.update(entity, ReflectionUtils.extractEntityAttributes(newEntity,entity));
    }

    @Override
    public void remove() {
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");

        try {
            this.dao.remove((T) JSONUtils.unmarshallSingleEntity(this.type,inputFile));
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Class<T> getType() {
        return null;
    }
}
