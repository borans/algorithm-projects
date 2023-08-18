import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {
    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename) throws ParserConfigurationException, IOException, SAXException {
        // TODO: YOUR CODE HERE

        Map<String, Malware> MalwareDB = new HashMap<>();

        File inputFile = new File(filename);

        DocumentBuilderFactory doc = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = doc.newDocumentBuilder();
        Document document = docBuilder.parse(inputFile);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("row");
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            Element elem = (Element) node;

            Malware m = new Malware(elem.getElementsByTagName("title").item(0).getTextContent(),
                    Integer.parseInt(elem.getElementsByTagName("level").item(0).getTextContent()),
                    elem.getElementsByTagName("hash").item(0).getTextContent());

            MalwareDB.put(elem.getElementsByTagName("hash").item(0).getTextContent(), m);

        }

        return MalwareDB;
    }
}
