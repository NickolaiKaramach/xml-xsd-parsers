package edu.etc.karamach.xml;

import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.XMLParserType;
import edu.etc.karamach.xml.factory.XMLMenuParserFactory;
import edu.etc.karamach.xml.parser.XMLMenuParser;

import java.util.List;

public class ExampleOfUsage {
    private static final String INPUT_XML = "repository/menu.xml";
    //TODO:Test?

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ExampleOfUsage.class.getClassLoader();
        String input = classLoader.getResource(INPUT_XML).getFile();

        XMLMenuParserFactory factory = XMLMenuParserFactory.getInstance();
        XMLMenuParser domMenuParser = factory.getXMLParser(XMLParserType.valueOf("DOM"));
        XMLMenuParser saxMenuParser = factory.getXMLParser(XMLParserType.valueOf("SAX"));
        XMLMenuParser staxMenuParser = factory.getXMLParser(XMLParserType.valueOf("STAX"));

        List<FoodMenu> domMenuResult = domMenuParser.parse(input);
        List<FoodMenu> saxmMenuResult = saxMenuParser.parse(input);
        List<FoodMenu> staxMenuResult = staxMenuParser.parse(input);

        if (domMenuResult.equals(saxmMenuResult) &&
                domMenuResult.equals(staxMenuResult)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }


}
