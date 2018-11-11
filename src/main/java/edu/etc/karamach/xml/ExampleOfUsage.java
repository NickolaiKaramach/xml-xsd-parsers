package edu.etc.karamach.xml;

import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.XMLParserType;
import edu.etc.karamach.xml.factory.XMLMenuParserFactory;
import edu.etc.karamach.xml.parser.XMLMenuParser;

import java.util.List;

public class ExampleOfUsage {
    //TODO:Test?
    //TODO:Replace it by relative path
    private static final String INPUT_XML = "/Users/kosoj_rus/IdeaProjects/xml-xsd/src/main/resources/menu.xml";

    public static void main(String[] args) throws Exception {
//        //SAX
//        XMLReader reader = XMLReaderFactory.createXMLReader();
//        SAXMenuParser saxParser = new SAXMenuParser(reader);
//        saxParser.parse(INPUT_XML);
//        SAXMenuHandler handler = saxParser.getHandler();
//
//        //StAX
//        InputStream fileInputStream = new FileInputStream(INPUT_XML);
//        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
//        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);
//
//        BreakfastMenu breakfastMenu = new BreakfastMenu();
//        MainMenu mainMenu = new MainMenu();
//        StAXMenuParser staxMenuParser = new StAXMenuParser(xmlStreamReader);
//
//        staxMenuParser.parse(breakfastMenu, mainMenu);
//
//        //DOM
//        BreakfastMenu breakfastMenuDOM = new BreakfastMenu();
//        MainMenu mainMenuDOM = new MainMenu();
//        DOMMenuParser domMenuParser = new DOMMenuParser();
//        domMenuParser.parse(INPUT_XML, breakfastMenuDOM, mainMenuDOM);


        //Output
//        System.out.println(breakfastMenuDOM.printMenu() + "\n\n" + mainMenuDOM.printMenu());
//
//        System.out.println(breakfastMenu.equals(breakfastMenuDOM));
//        System.out.println(mainMenu.equals(mainMenuDOM));
//        System.out.println(handler.getBreakfastMenu().equals(breakfastMenu));
//        System.out.println(handler.getMainMenu().equals(mainMenu));
        XMLMenuParserFactory factory = XMLMenuParserFactory.getInstance();
        XMLMenuParser domMenuParser = factory.getXMLParser(XMLParserType.valueOf("DOM"));
        XMLMenuParser saxMenuParser = factory.getXMLParser(XMLParserType.valueOf("SAX"));
        XMLMenuParser staxMenuParser = factory.getXMLParser(XMLParserType.valueOf("STAX"));

        List<FoodMenu> domMenuResult = domMenuParser.parse(INPUT_XML);
        List<FoodMenu> saxmMenuResult = saxMenuParser.parse(INPUT_XML);
        List<FoodMenu> staxMenuResult = staxMenuParser.parse(INPUT_XML);

        if (domMenuResult.equals(saxmMenuResult) &&
                domMenuResult.equals(staxMenuResult)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }


}
