package travelAgency.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.util.interfaces.DataMarshaller;
import travelAgency.util.ReflectionUtils;
import travelAgency.util.enums.Entities;

import java.util.List;

public class JSONService<T> extends AbstractService<T> {
    private static final Logger logger = LogManager.getLogger(JSONService.class);
    private DataMarshaller marshaller;
    private final String outputPath = "target/";;

    public JSONService(Class<T> entityClass,DataMarshaller marshaller){
        super(entityClass);
        this.marshaller = marshaller;
    }

    @Override
    public void findById() {
        System.out.println("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.daoStrategy.getById(id);
        if(entity == null){
            logger.warn("{} not found.", this.type.getSimpleName());
            return;
        }
        try {
            marshaller.marshallSingleEntity(entity,outputPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll() {
        List<T> entities = daoStrategy.getAll();
        marshaller.marshallListEntity(Entities.getPluralClassForEntity(this.type),entities,outputPath);
    }

    @Override
    public void add() {
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");
        this.daoStrategy.save((T) marshaller.unmarshallSingleEntity(inputFile,this.type));
    }

    @Override
    public void update() {
        System.out.print("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.daoStrategy.getById(id);
        if (entity == null) {
            logger.warn("{} not found.", this.type.getSimpleName());
            return;
        }
        // read from JSON
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");
        T newEntity;
        newEntity = ((T) marshaller.unmarshallSingleEntity(inputFile,this.type));
        this.daoStrategy.update(entity, ReflectionUtils.extractEntityAttributes(newEntity,entity));
        logger.info("Entity updated");
    }

    @Override
    public void remove() {
        String inputFile = MenuService.askForInput("Please enter the complete name of the JSON File to parse under /resources: ");
        this.daoStrategy.remove((T) marshaller.unmarshallSingleEntity(inputFile,this.type));
        logger.error("Error while deleting entity");
        logger.info("Entity deleted");
    }

    @Override
    public Class<T> getType() {
        return null;
    }
}
