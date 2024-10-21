package travelAgency.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import travelAgency.util.enums.Entities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtils {

    public static Object unmarshall_single_entity(Class<?> entityClass, String jsonFilePath) {
        File inputFile = new File(jsonFilePath);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Object entity = null;
        try {
            entity = mapper.readValue(inputFile, entityClass);
            System.out.println("Unmarshalled Single Entity: " + entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public static List<?> unmarshall_list_entity(Class<?> entityClass, String jsonFilePath) {
        if (Entities.getPluralClassForEntity(entityClass) == null) {
            try {
                throw new Exception("EntityClass error"); // passing a plural Class or just a class that doesn't belong to hierarchy
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        String pluralClassName = Entities.getPluralClassForEntity(entityClass).getSimpleName(); //ex if customer get customers class name
        File inputFile = new File(jsonFilePath);
        List<?> listOfEntities;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            JsonNode rootNode = mapper.readTree(inputFile);
            listOfEntities = mapper.readerForListOf(entityClass).readValue(rootNode.get(pluralClassName));
            System.out.println("Unmarshalled List Entity: " + listOfEntities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfEntities;
    }

    public static Object unmarshall_hierarchy(List<Class<?>> entitiesClasses, String jsonFilePath){

        File inputFile = new File(jsonFilePath);
        Map<Class<?>, Object> entitiesMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // to work with java 8 dates?
        try {
            JsonNode rootNode = mapper.readTree(inputFile);

            for (Class<?> entityClass : entitiesClasses) {
                String node = entityClass.getSimpleName();
                JsonNode entityNode = rootNode.get(node);
                if (entityNode != null) {
                    Object entity = mapper.treeToValue(entityNode, entityClass);
                    entitiesMap.put(entityClass, entity);
                    System.out.println("Unmarshalled Entity (" + node + "): " + entity);
                } else {
                    System.out.println("No JSON node found for entity: " + node);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while reading or parsing the JSON file.");
        }
        return entitiesMap;
    }

    public static void marshall_list_entity(Class<?> pluralEntityClass,List<?> entityList) throws IOException {
        String outputDir = "target/";
        String outputFile = outputDir+pluralEntityClass.getSimpleName()+System.currentTimeMillis()+".json";

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), entityList);

    }

    public static void marshall_single_entity(Class<?> entityClass,Object entity) throws IOException {
        String outputDir = "target/";
        String outputFile = outputDir+entityClass.getSimpleName()+System.currentTimeMillis()+".json";

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), entity);

    }


    public static void main(String[] args){
        //unmarshall_single_entity(Entities.Customer.getEntityClass(),"src/main/resources/customerTest.json");
        //unmarshall_single_entity(Entities.Employee.getEntityClass(),"src/main/resources/employeeTest.json");
        //unmarshall_list_entity(Entities.Customer.getEntityClass(),"src/main/resources/customersTest.json");
        //unmarshall_hierarchy(List.of(Entities.Customer.getEntityClass(),Entities.Employee.getEntityClass(),Entities.Booking.getEntityClass(),
        //        Entities.Hotel.getEntityClass(),Entities.Excursion.getEntityClass()),
        //        "src/main/resources/inputData.json");


    }
}
