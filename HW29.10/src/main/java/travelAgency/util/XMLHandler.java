package travelAgency.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import travelAgency.util.enums.Entities;
import travelAgency.util.interfaces.DataMarshaller;
import travelAgency.util.interfaces.XMLValidator;
import travelAgency.util.interfaces.XmlParse;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLHandler implements DataMarshaller, XmlParse, XMLValidator {

    private static final Logger logger = LogManager.getLogger(XMLHandler.class);
    private final static String inputDir = "src/main/resources/";

    @Override
    public boolean validateFiles(String xmlFilePath,String xsdFilepath) {
        try {
            File xmlFile = new File(xmlFilePath);
            File xsdFile = new File(xsdFilepath);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema xsdSchema = factory.newSchema(xsdFile);
            Validator validator = xsdSchema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException | IOException e) {
            logger.error(e);
            return false; // if invalid exc is raised?
        }
        return true;
    }

    @Override
    public void parseXmlFile(String xmlFilePath){
        File inputFile = new File(xmlFilePath);
        try{

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize(); //remove whitespaces

            Element root = document.getDocumentElement(); // root element
            logger.info("Root Node: {}", root.getNodeName());
            NodeList nodeList = root.getChildNodes(); // get children from root

            for(int i=0;i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);

                //is the node an element node?
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    logger.info("Node name: {}", element.getNodeName());

                    //does the node have attributes?
                    if(element.hasAttributes()){
                        for(int j = 0; j < element.getAttributes().getLength(); j++){
                            Node attribute = element.getAttributes().item(j);
                            logger.info("\tAttribute: {}", attribute);
                        }
                    }

                    // Loop through each child node of the element
                    NodeList childNodes = element.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node child = childNodes.item(j);

                        // Check if the child node is an element node
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) child;
                            logger.info("\tChild Node: {} = {}", childElement.getNodeName(), childElement.getTextContent());
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void marshallSingleEntity(Object entity, String outputPath) {
        String outputFile = outputPath+entity.getClass().getSimpleName()+System.currentTimeMillis()+".xml";
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(entity.getClass());
            Marshaller mar= null;
            mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(entity, new File(outputFile));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object unmarshallSingleEntity(String inputPath, Class<?> entityClass) {
        File xmlFile = new File(inputDir+inputPath);

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(entityClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void marshallListEntity(Class<?> className, List<?> entities, String outputPath) {
        Class<?> pluralClass = Entities.getPluralClassForEntity(className);
        String outputFile = outputPath+pluralClass.getSimpleName()+System.currentTimeMillis()+".xml";

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(pluralClass);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(ReflectionUtils.getPluralList(pluralClass,entities), new File(outputFile));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<?> unmarshallListEntity(String inputPath, Class<?> entityClass) {
        File xmlFile = new File(inputPath);
        try {
            JAXBContext context = JAXBContext.newInstance(entityClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (List<?>) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
