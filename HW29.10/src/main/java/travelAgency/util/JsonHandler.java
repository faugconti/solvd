package travelAgency.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.util.interfaces.DataMarshaller;
import travelAgency.util.interfaces.UnmarshallHierarchy;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonHandler implements DataMarshaller, UnmarshallHierarchy {

    private final static String inputDir = "src/main/resources/";
    private static final Logger logger = LogManager.getLogger(JsonHandler.class);

    @Override
    public void unmarshallHierarchy(List<Class<?>> entitiesClasses, String filePath) {
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


    @Override
    public void marshallSingleEntity(Object entity, String outputPath) {
        String outputFileName = outputPath + entity.getClass().getSimpleName() + System.currentTimeMillis() + ".json";
        File outputFile = new File(outputFileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object unmarshallSingleEntity(String inputPath, Class<?> entityClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Object entity = null;
        try {
            entity = mapper.readValue(new File(inputDir + inputPath), entityClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void marshallListEntity(Class<?> className,List<?> entities,String outputPath) {
        String outputFile = outputPath + className.getSimpleName() + System.currentTimeMillis() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<?> unmarshallListEntity(String inputPath, Class<?> entityClass) {
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
}
