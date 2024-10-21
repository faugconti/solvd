package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.EntityDAO;
import travelAgency.util.JSONUtils;
import travelAgency.util.enums.Entities;

import java.io.IOException;
import java.util.List;

public class JSONService<T> implements Services<T> {
    public final DAO<T> dao;
    private final Class<T> type;
    private final String baseDirectory = "src/main/resources/";

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
            System.out.println("No "+this.type.getSimpleName()+" found with that id");
            return;
        }
        try {
            JSONUtils.marshall_single_entity(this.type,entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void findAll() {
        List<T> entities = dao.getAll();
        try{
            JSONUtils.marshall_list_entity(Entities.getPluralClassForEntity(this.type),entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add() {
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");
        this.dao.save((T) JSONUtils.unmarshall_single_entity(this.type,this.baseDirectory+inputFile));
    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");
        this.dao.remove((T) JSONUtils.unmarshall_single_entity(this.type,this.baseDirectory+inputFile));
    }

    @Override
    public Class<T> getType() {
        return null;
    }
}
