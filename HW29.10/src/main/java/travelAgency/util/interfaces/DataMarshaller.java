package travelAgency.util.interfaces;

import java.util.List;

public interface DataMarshaller {
    void marshallSingleEntity(Object entity, String outputPath);
    Object unmarshallSingleEntity(String inputPath, Class<?> entityClass);

    void marshallListEntity(Class<?> className,List<?> entities, String outputPath);
    List<?> unmarshallListEntity(String inputPath, Class<?> entityClass);
}