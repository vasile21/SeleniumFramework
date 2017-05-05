/**
 * Created by Vasile.Vetisan on 4/25/2017.
 */

package utilities;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XMLUtilities {

    public static Document readXMLFile(String filePathName) {
        Document xmlDoc = null;
        try {
            File fXmlFile = new File(filePathName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            xmlDoc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlDoc;
    }

    public static String getRootNode(Document xmlDoc) {
        String rootElement = xmlDoc.getDocumentElement().getNodeName();
        System.out.println("Root element :" + rootElement);
        return rootElement;
    }

    public static NodeList getElementsList(Document xmlDoc, String nodeName) {
        return xmlDoc.getElementsByTagName(nodeName);

    }

    public static Map<String, String> getContactsChildNamesAndValues(Document xmlDocument, String nodeName, int idx) {
        Map<String, String> contactFields = new HashMap<String, String>();
        xmlDocument.getDocumentElement().normalize();
        NodeList nList = xmlDocument.getElementsByTagName(nodeName);
        NodeList childs = nList.item(idx).getChildNodes();
       for(int i=0; i< childs.getLength(); i++){
           Node childNode = childs.item(i);
            if(childNode.getNodeType() == Node.ELEMENT_NODE) {
                contactFields.put(childNode.getNodeName(), childNode.getTextContent());
            }
        }

        return contactFields;
    }

    public static Map<String, String> getContactsEmailsInfo(Document xmlDocument, String nodeName, int idx) {
        Map<String, String> contactsExtraInformation = new HashMap<String, String>();
        xmlDocument.getDocumentElement().normalize();
        NodeList nList = xmlDocument.getElementsByTagName(nodeName);
        NodeList childs = nList.item(idx).getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            Node tempNode = childs.item(i);
            if(tempNode.getNodeType() == Node.ELEMENT_NODE && tempNode.hasAttributes())
            {
                NamedNodeMap nodeMap = tempNode.getAttributes();
                for (int j = 0; j < nodeMap.getLength(); j++)
                {
                    Node node = nodeMap.item(j);
                    //System.out.println("Attr. name: " + node.getNodeName());
                    //System.out.println("Attr. value: " + node.getNodeValue());
                    contactsExtraInformation.put(node.getNodeName(),node.getNodeValue());
                }
            }
        }
        return contactsExtraInformation;
    }
}
