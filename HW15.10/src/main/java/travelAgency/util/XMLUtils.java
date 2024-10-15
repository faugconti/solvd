package travelAgency.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

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

    public static void main(String args[]){
        String xmlFilePath = "src/main/resources/travelAgency.xml";
        String xsdFilePath = "src/main/resources/travelAgency.xsd";
        System.out.println("¿Are the files valid?: "+validateFiles(xmlFilePath,xsdFilePath));
        parseXmlFile(xmlFilePath);
    }
}
