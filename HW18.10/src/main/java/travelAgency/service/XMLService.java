package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.EntityDAO;
import travelAgency.util.JSONUtils;
import travelAgency.util.XMLUtils;
import travelAgency.util.enums.Entities;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class XMLService<T> implements Services<T> {

    public final DAO<T> dao;
    private final Class<T> type;

    public XMLService(Class<T> entityClass){
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
        }try {
            XMLUtils.marshall_single_entity(this.type,entity);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll() {

        List<T> entities = dao.getAll();
        try {
            XMLUtils.marshall_list(Entities.getPluralClassForEntity(this.type),entities);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add() {

    String baseDirectory = "src/main/resources/";
    //ask for name of XML in /resources folder
    String inputFile = MenuService.askForInput("Please enter the complete name of the XML File to parse under /resources: ");
        try {
            this.dao.save((T) XMLUtils.JAXunmarshaller(this.type,baseDirectory+inputFile));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update() {
    }

    @Override
    public void remove() {
        String baseDirectory = "src/main/resources/";
        //ask for name of XML in /resources folder
        String inputFile = MenuService.askForInput("Please enter the complete name of the XML File to parse under /resources: ");

        try {
            this.dao.remove((T) XMLUtils.JAXunmarshaller(this.type,baseDirectory+inputFile));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Class<T> getType() {
        return this.type;
    }
}