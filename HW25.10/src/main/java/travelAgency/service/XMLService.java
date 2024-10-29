package travelAgency.service;

import travelAgency.DAO.DAO;
import travelAgency.DAO.JDBC.EntityDAO;
import travelAgency.DAO.MyBatis.MyBatisDAO;
import travelAgency.util.ReflectionUtils;
import travelAgency.util.XMLUtils;
import travelAgency.util.enums.Entities;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class XMLService<T> implements Services<T> {

    public final DAO<T> dao;
    private final Class<T> type;

    public XMLService(Class<T> entityClass){
        this.dao = new MyBatisDAO<>(entityClass);
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
            XMLUtils.marshallSingleEntity(this.type,entity);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll() {
        List<T> entities = dao.getAll();
        try {
            XMLUtils.marshallList(Entities.getPluralClassForEntity(this.type),entities);
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
        List<String> excludeFields = List.of("id" + this.getType().getSimpleName());
        System.out.print("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if (entity == null) {
            System.out.println("No row found for that ID.");
            return;
        }
        String baseDirectory = "src/main/resources/";
        // read from XML
        String inputFile = MenuService.askForInput("Please enter the complete name of the XML File to parse under /resources: ");
        T newEntity;
        try {
            newEntity = ((T) XMLUtils.JAXunmarshaller(this.type, baseDirectory + inputFile));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        this.dao.update(entity, ReflectionUtils.extractEntityAttributes(newEntity,entity));

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