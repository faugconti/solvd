package travelAgency.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.text.SimpleDateFormat;


public class ReflectionUtils {

    @SneakyThrows
    public static Object askForEntityAttributes(Class<?> entityClass,List<String> excludeFields)  {

        if (excludeFields == null){
            excludeFields = new ArrayList<>();
        }

        Object entityInstance = entityClass.getDeclaredConstructor().newInstance();

        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true); // Make private fields accessible

            String fieldType = field.getType().getSimpleName();
            String fieldName = field.getName();

            if (excludeFields.contains(fieldName)) {
                continue;
            }

            System.out.println("Enter "+fieldName + "("+fieldType+"): ");
            String inputValue = new Scanner(System.in).nextLine();

            // use boxing for converting the data types so we dont get errors
            Object convertedValue = convertInputToFieldType(inputValue, field.getType());
            field.set(entityInstance, convertedValue);
        }

        return entityInstance;
    }

    private static Object convertInputToFieldType(String input, Class<?> fieldType) {

        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(input);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(input);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(input);
        } else if (fieldType == String.class) {
            return input;
        } else if (fieldType == LocalDate.class) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(input, formatter);
                return localDate;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date as yyyy-MM-dd.");
                return null;
            }
        }
        return null;
    }

    public static Object getPluralList(Class<?> pluralClass, List<?> singularList){
        Object wrapper = null;
        try {
            wrapper = pluralClass.getDeclaredConstructor().newInstance();
            Method setter = pluralClass.getMethod("set"+pluralClass.getSimpleName(),List.class);
            setter.invoke(wrapper,singularList);
        }  catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException  e){
            throw new RuntimeException(e);
        }
        return wrapper;
    }

    public static <T> Class<T> getEntityClass(String entityName) {
        try {
            return (Class<T>) Class.forName("travelAgency.model." + entityName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Entity class not found: " + entityName, e);
        }
    }

    public static Object setID(Object obj,int newId){
        Field field = null;
        try {
            field = obj.getClass().getDeclaredField("id"+obj.getClass().getSimpleName());
            field.setAccessible(true);
            try {
                field.set(obj,newId);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    return obj;
    }


    public static String[] extractEntityAttributes(Object newEntity, Object oldEntity) {
        List<String> attributes = new ArrayList<>();

        Field[] fields = newEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object newValue = field.get(newEntity);
                Object oldValue = field.get(oldEntity);

                Object finalValue = newValue;

                // check for null strings
                if (newValue == null || (newValue instanceof String && ((String) newValue).isEmpty())) {
                    finalValue = oldValue;
                }

                if (finalValue != null) {
                    attributes.add(finalValue.toString());
                } else {
                    attributes.add("null");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return attributes.toArray(new String[0]);
    }


}
