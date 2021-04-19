

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;

/**
 * references: https://crunchify.com/java-xml-xpath-parser-how-to-parse-xml-document-using-xpath-in-java/
 * https://stackoverflow.com/questions/67168726/xpath-expression-for-xml-response-need-condition
 */
public class XmlPathExample {

    private static String xmlValue =
            "            <parent>\n" +
                    "      <child>\n" +
                    "        <f1>abh</f1>\n" +
                    "        <f2>jb</f2>\n" +
                    "      </child>\n" +
                    "      <child>\n" +
                    "        <f1>abh2</f1>\n" +
                    "        <f2>jb2</f2>\n" +
                    "      </child>\n" +
                    "    </parent>";
    public static void main(String[] args) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {

        try {


            String expression = "/parent/child[f2=\"jb2\"]/f1/text()";

            Node node = getNodeFromFile(expression);
            System.out.println("=============================");
            System.out.println(node.getNodeName());
            System.out.println("=============================");
            System.out.println(node.getNodeValue());
            System.out.println("1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1");

            System.out.println("=============================");
            System.out.println(getNodeFromValue(expression));



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Node getNodeFromFile(String expression) {
        Node node = null;
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document xmlDocument = builder.parse("src/main/resources/parentChild.xml");

            XPath xPath = XPathFactory.newInstance().newXPath();

            node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);

        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return node;
    }
    public static String getNodeFromValue(String expression) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlValue)));

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath
                    .compile(expression);
            return (String) expr.evaluate(document, XPathConstants.STRING);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
