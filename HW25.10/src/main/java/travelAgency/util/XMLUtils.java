package travelAgency.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import travelAgency.util.enums.Entities;

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

public class XMLUtils {

    public static boolean validateFiles(String xmlFilePath,String xsdFilepath) {
        try {
            File xmlFile = new File(xmlFilePath);
            File xsdFile = new File(xsdFilepath);
            System.out.println(xmlFile);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema xsdSchema = factory.newSchema(xsdFile);
            Validator validator = xsdSchema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException | IOException e) {
            System.out.println(e);
            return false; // if invalid exc is raised?
        }
        return true;
    }

    //DOM Parser
    public static void parseXmlFile(String xmlFilePath){
        File inputFile = new File(xmlFilePath);
        try{

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize(); //remove whitespaces

            Element root = document.getDocumentElement(); // root element
            System.out.println("Root Node: "+root.getNodeName());
            NodeList nodeList = root.getChildNodes(); // get children from root

            for(int i=0;i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);

                //is the node an element node?
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    System.out.println("Node name: "+element.getNodeName());

                    //does the node have attributes?
                    if(element.hasAttributes()){
                        for(int j = 0; j < element.getAttributes().getLength(); j++){
                            Node attribute = element.getAttributes().item(j);
                            System.out.println("\tAttribute: "+attribute);
                        }
                    }

                    // Loop through each child node of the element
                    NodeList childNodes = element.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node child = childNodes.item(j);

                        // Check if the child node is an element node
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) child;
                            System.out.println("\tChild Node: " + childElement.getNodeName() + " = " + childElement.getTextContent());
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Object JAXunmarshaller(Entities entity, String xmlFilePath) throws JAXBException {
        File xmlFile = new File(xmlFilePath);

        JAXBContext context = JAXBContext.newInstance(entity.getEntityClass());
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(xmlFile);
    }

    public static Object JAXunmarshaller(Class<?> entityClass, String xmlFilePath) throws JAXBException {


        File xmlFile = new File(xmlFilePath);
        System.out.println("Reading "+ entityClass+" "+xmlFilePath);
        JAXBContext context = JAXBContext.newInstance(entityClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(xmlFile);
    }

    public static void marshallList(Class<?> pluralClass, List<?> singularList) throws JAXBException,IOException{
        String outputDir = "target/";
        String outputFile = outputDir+pluralClass.getSimpleName()+System.currentTimeMillis()+".xml";

        Object wrapper = ReflectionUtils.getPluralList(pluralClass,singularList);
        JAXBContext context = JAXBContext.newInstance(pluralClass);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(wrapper, new File(outputFile));
    }

    public static void marshallSingleEntity(Class<?> entityClass,Object entity) throws JAXBException {
        String outputDir = "target/";
        String outputFile = outputDir+entityClass.getSimpleName()+System.currentTimeMillis()+".xml";
        JAXBContext context = JAXBContext.newInstance(entityClass);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(entity, new File(outputFile));
    }

    public static void main(String args[]) throws JAXBException {
        String xmlFilePath = "src/main/resources/travelAgency.xml";
        String xsdFilePath = "src/main/resources/travelAgency.xsd";
        //System.out.println("¿Are the files valid?: "+validateFiles(xmlFilePath,xsdFilePath));
        //parseXmlFile(xmlFilePath);
        System.out.println(JAXunmarshaller(Entities.Customer,"src/main/resources/customerTest.xml"));

    }
}
