package edu.etc.karamach.xml;

import edu.etc.karamach.xml.dom.DOMMenuParser;
import edu.etc.karamach.xml.entity.Menu.BreakfastMenu;
import edu.etc.karamach.xml.entity.Menu.MainMenu;
import edu.etc.karamach.xml.sax.SAXMenuParser;
import edu.etc.karamach.xml.sax.handler.SAXMenuHandler;
import edu.etc.karamach.xml.stax.StAXMenuParser;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;

public class ExampleOfUsage {
    //TODO:Test?
    //TODO:Replace it by relative path
    private static final String INPUT_XML = "/Users/kosoj_rus/IdeaProjects/xml-xsd/src/main/resources/menu.xml";

    public static void main(String[] args) throws Exception {
        //SAX
        XMLReader reader = XMLReaderFactory.createXMLReader();
        SAXMenuParser saxParser = new SAXMenuParser(reader);
        saxParser.parse(INPUT_XML);
        SAXMenuHandler handler = saxParser.getHandler();

        //StAX
        InputStream fileInputStream = new FileInputStream(INPUT_XML);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

        BreakfastMenu breakfastMenu = new BreakfastMenu();
        MainMenu mainMenu = new MainMenu();
        StAXMenuParser staxMenuParser = new StAXMenuParser(xmlStreamReader);

        staxMenuParser.parse(breakfastMenu, mainMenu);

        //DOM
        BreakfastMenu breakfastMenuDOM = new BreakfastMenu();
        MainMenu mainMenuDOM = new MainMenu();
        DOMMenuParser domMenuParser = new DOMMenuParser();
        domMenuParser.parse(INPUT_XML, breakfastMenuDOM, mainMenuDOM);


        //Output
        System.out.println(breakfastMenuDOM.printMenu() + "\n\n" + mainMenuDOM.printMenu());

        System.out.println(breakfastMenu.equals(breakfastMenuDOM));
        System.out.println(mainMenu.equals(mainMenuDOM));
        System.out.println(handler.getBreakfastMenu().equals(breakfastMenu));
        System.out.println(handler.getMainMenu().equals(mainMenu));
    }


}
