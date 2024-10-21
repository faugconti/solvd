package travelAgency.util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    public static void marshall_list_entity(Class<?> pluralClass, List<?> entities) throws IOException {
        String outputFile = outputDir+pluralClass.getSimpleName()+System.currentTimeMillis()+".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile),entities);
    }

    public static void marshall_single_entity(Class<?> entityClass,Object entity ){
        String outputFileName = outputDir+entityClass.getSimpleName()+System.currentTimeMillis()+".json";
        File outputFile = new File(outputFileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object unmarshall_single_entity(Class<?> entityClass,String inputPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Object entity = mapper.readValue(new File(inputDir+inputPath),entityClass);
        return entity;
    }

    public static List<?> unmarshall_list_entity(Class<?> entityClass,String inputPath){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(
                    new File(inputPath),
                    mapper.getTypeFactory().constructCollectionType(List.class, entityClass)
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void unmarshall_hierarchy(List<Class<?>> entitiesClasses, String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            JsonNode rootNode = mapper.readTree(new File(filePath));
            //Map<Class<?>, Object> entitiesMap = new HashMap<>();

            for (Class<?> entityClass : entitiesClasses) {
                String nodeName = entityClass.getSimpleName();

                JsonNode node = rootNode.get(nodeName);
                if (node != null) {
                    Object entity = mapper.treeToValue(node, entityClass);
                    //entitiesMap.put(entityClass, entity);
                    System.out.println("Unmarshalled Entity (" + nodeName + "): " + entity);
                } else {
                    System.out.println("No JSON node found for entity: " + nodeName);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while reading or parsing the JSON file.");
        }
    }
    public static void main(String[] args){
        unmarshall_hierarchy(List.of(Entities.Customer.getEntityClass(),Entities.Employee.getEntityClass(),Entities.Hotel.getEntityClass(),
                Entities.Booking.getEntityClass(),Entities.Excursion.getEntityClass()),"src/main/resources/Entities.json");;


        System.out.println(unmarshall_list_entity(Entities.Customer.getEntityClass(), "src/main/resources/customersTest.json"));
    }
}


