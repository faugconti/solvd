package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.EntityDAO;
import travelAgency.util.JSONUtils;
import travelAgency.util.XMLUtils;
import travelAgency.util.enums.Entities;

import javax.xml.bind.JAXBException;
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
            JSONUtils.marshall_single_entity(this.type,entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll() {
        List<T> entities = dao.getAll();
        try {
            JSONUtils.marshall_list_entity(Entities.getPluralClassForEntity(this.type),entities);
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add() {
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");
        try {
            this.dao.save((T) JSONUtils.unmarshall_single_entity(this.type,inputFile));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");

        try {
            this.dao.remove((T) JSONUtils.unmarshall_single_entity(this.type,inputFile));
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Class<T> getType() {
        return null;
    }
}
