package travelAgency.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.util.interfaces.DataMarshaller;
import travelAgency.util.ReflectionUtils;
import java.util.List;

    public class XMLService<T> extends AbstractService<T> {
        private static final Logger logger = LogManager.getLogger(XMLService.class);
        private final DataMarshaller marshaller;
        private final String outputPath = "target/";;

        public XMLService(Class<T> entityClass,DataMarshaller marshaller){
            super(entityClass);
            this.marshaller = marshaller;
        }
    
        @Override
        public void findById() {
            System.out.println("ID: ");
            int id = MenuService.getIntegerInput();
            T entity = this.daoStrategy.getById(id);
            if(entity == null){
                logger.error("{} not found.", this.type.getSimpleName());
                return;
            }
            marshaller.marshallSingleEntity(entity,outputPath);
        }
    
        @Override
        public void findAll() {
            List<T> entities = daoStrategy.getAll();
            marshaller.marshallListEntity(this.type,entities,outputPath);
        }
    
        @Override
        public void add() {
        //ask for name of XML in /resources folder
        String inputFile = MenuService.askForInput("Please enter the complete name of the XML File to parse under /resources: ");
        this.daoStrategy.save((T) marshaller.unmarshallSingleEntity(inputFile,this.type));
            logger.info("Record added to Database");
        }
    
    
        @Override
        public void update() {
            List<String> excludeFields = List.of("id" + this.getType().getSimpleName());
            System.out.print("ID: ");
            int id = MenuService.getIntegerInput();
            T entity = this.daoStrategy.getById(id);
            if (entity == null) {
                logger.error("No row found for that ID.");
                return;
            }
            String baseDirectory = "src/main/resources/";
            // read from XML
            String inputFile = MenuService.askForInput("Please enter the complete name of the XML File to parse under /resources: ");
            T newEntity;
            newEntity = ((T) marshaller.unmarshallSingleEntity( inputFile,this.type));
            this.daoStrategy.update(entity, ReflectionUtils.extractEntityAttributes(newEntity,entity));
            logger.info("Record updated");
    
        }
    
        @Override
        public void remove() {
            String baseDirectory = "src/main/resources/";
            //ask for name of XML in /resources folder
            String inputFile = MenuService.askForInput("Please enter the complete name of the XML File to parse under /resources: ");
            this.daoStrategy.remove((T) marshaller.unmarshallSingleEntity(inputFile,this.type));
            logger.info("Record deleted from Database");
        }
    
        @Override
        public Class<T> getType() {
            return this.type;
        }
    }