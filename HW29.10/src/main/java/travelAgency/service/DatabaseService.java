package travelAgency.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.util.ReflectionUtils;

import java.util.List;

public class DatabaseService<T> extends AbstractService<T> {

    private static final Logger logger = LogManager.getLogger(DatabaseService.class);

    public DatabaseService(Class<T> entityClass){
        super(entityClass);
    }

    @Override
    public void findById() {
        System.out.println("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if(entity == null){
            logger.warn("No records found");
        }else{
            logger.info("Entity found {}",entity);
        }
    }

    @Override
    public void findAll() {
        List<T> entities = this.dao.getAll();
        if (entities.isEmpty()) {
            logger.warn("No records found");
        } else {
            entities.forEach(logger::info);
        }
    }

    @Override
    public void add() {
        T entity = (T) ReflectionUtils.askForEntityAttributes(this.type,null);
        this.dao.save(entity);
        logger.info("Record added to Database");
    }

    @Override
    public void update() {;
        List<String> excludeFields = List.of("id"+this.getType().getSimpleName());
        System.out.print("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if(entity == null){
            logger.warn("No records found");
            return;
        }
        logger.info("Record found {}",entity);
        T newEntity = (T) ReflectionUtils.askForEntityAttributes(this.type,excludeFields); //create a new object
        newEntity = (T) ReflectionUtils.setID(newEntity,id); // object with modified attributes.
        this.dao.update(entity,ReflectionUtils.extractEntityAttributes(newEntity,entity));
    }

    @Override
    public void remove() {
        System.out.println("ID: ");
        int id = MenuService.getIntegerInput();
        T entity = this.dao.getById(id);
        if(entity == null){
            logger.warn("{} not found.", this.type.getSimpleName());
            return;
        }
        this.dao.remove((T) entity);
        logger.info("Record deleted from Database");
    }

    @Override
    public Class<T> getType() {
        return this.type;
    }
}
