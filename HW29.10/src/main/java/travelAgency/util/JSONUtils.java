package travelAgency.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.util.enums.Entities;

import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtils {

    private final static String outputDir = "target/";
    private final static String inputDir = "src/main/resources/";
    private static final Logger logger = LogManager.getLogger(JSONUtils.class);


    public static void marshallListEntity(Class<?> pluralClass, List<?> entities) throws IOException {
        String outputFile = outputDir + pluralClass.getSimpleName() + System.currentTimeMillis() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), entities);
    }

    public static void marshallSingleEntity(Class<?> entityClass, Object entity) {
        String outputFileName = outputDir + entityClass.getSimpleName() + System.currentTimeMillis() + ".json";
        File outputFile = new File(outputFileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object unmarshallSingleEntity(Class<?> entityClass, String inputPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Object entity = mapper.readValue(new File(inputDir + inputPath), entityClass);
        return entity;
    }

    public static List<?> unmarshallListEntity(Class<?> entityClass, String inputPath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(
                    new File(inputPath),
                    mapper.getTypeFactory().constructCollectionType(List.class, entityClass));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void unmarshallHierarchy(List<Class<?>> entitiesClasses, String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            JsonNode rootNode = mapper.readTree(new File(filePath));
            // Map<Class<?>, Object> entitiesMap = new HashMap<>();

            for (Class<?> entityClass : entitiesClasses) {
                String nodeName = entityClass.getSimpleName();

                JsonNode node = rootNode.get(nodeName);
                if (node != null) {
                    Object entity = mapper.treeToValue(node, entityClass);
                    // entitiesMap.put(entityClass, entity);
                    logger.info("Unmarshalled Entity ({}): {}", nodeName, entity);
                } else {
                    logger.warn("No JSON node found for entity: {}", nodeName);
                }
            }
        } catch (IOException e) {
            logger.error("Error while reading or parsing the JSON file.");
        }
    }

    public static void main(String[] args) {
        unmarshallHierarchy(
                List.of(Entities.Customer.getEntityClass(), Entities.Employee.getEntityClass(),
                        Entities.Hotel.getEntityClass(),
                        Entities.Booking.getEntityClass(), Entities.Excursion.getEntityClass()),
                "src/main/resources/Entities.json");
        ;

        logger.info(
                unmarshallListEntity(Entities.Customer.getEntityClass(), "src/main/resources/customersTest.json"));
    }
}
