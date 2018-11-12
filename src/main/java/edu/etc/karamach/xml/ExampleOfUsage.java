package edu.etc.karamach.xml;

import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.XMLParserType;
import edu.etc.karamach.xml.exception.XMLParserException;
import edu.etc.karamach.xml.exception.XMLParserFactoryException;
import edu.etc.karamach.xml.factory.XMLMenuParserFactory;
import edu.etc.karamach.xml.parser.XMLMenuParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ExampleOfUsage {
    private static final String INPUT_XML = "repository/menu.xml";
    private static final Logger logger = LogManager.getLogger("default");

    public static void main(String[] args) {

        ClassLoader classLoader = ExampleOfUsage.class.getClassLoader();
        String input = classLoader.getResource(INPUT_XML).getFile();

        try {
            XMLMenuParserFactory factory = XMLMenuParserFactory.getInstance();
            XMLMenuParser domMenuParser = factory.getXMLParser(XMLParserType.valueOf("DOM"));
            XMLMenuParser saxMenuParser = factory.getXMLParser(XMLParserType.valueOf("SAX"));
            XMLMenuParser staxMenuParser = factory.getXMLParser(XMLParserType.valueOf("STAX"));

            List<FoodMenu> domMenuResult = domMenuParser.parse(input);
            List<FoodMenu> saxmMenuResult = saxMenuParser.parse(input);
            List<FoodMenu> staxMenuResult = staxMenuParser.parse(input);

            if (domMenuResult.equals(saxmMenuResult) &&
                    domMenuResult.equals(staxMenuResult)) {
                logger.info("yes");
            } else {
                logger.info("no");
            }
        } catch (XMLParserFactoryException | XMLParserException e) {
            logger.error("Error!");
        }


    }


}
