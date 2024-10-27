package travelAgency.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.util.ReflectionUtils;
import travelAgency.util.XMLUtils;
import travelAgency.util.enums.Entities;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class XMLService<T> extends AbstractService<T> {
    private static final Logger logger = LogManager.getLogger(XMLService.class);

    public XMLService(Class<T> entityClass){
        super(entityClass);
    }

    @Override
    public void findById() {
        System.out.println("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if(entity == null){
            logger.error("{} not found.", this.type.getSimpleName());
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
        logger.info("Record added to Database");
    }


    @Override
    public void update() {
        List<String> excludeFields = List.of("id" + this.getType().getSimpleName());
        System.out.print("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if (entity == null) {
            logger.error("No row found for that ID.");
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
        logger.info("Record updated");

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
        logger.info("Record deleted from Database");
    }

    @Override
    public Class<T> getType() {
        return this.type;
    }
}