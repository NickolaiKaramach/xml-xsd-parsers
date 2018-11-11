package edu.etc.karamach.xml.parser.stax;

import edu.etc.karamach.xml.entity.Food;
import edu.etc.karamach.xml.entity.Menu.BreakfastMenu;
import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.Menu.MainMenu;
import edu.etc.karamach.xml.entity.MenuTagName;
import edu.etc.karamach.xml.exception.StAXMenuParserException;
import edu.etc.karamach.xml.exception.XMLParserException;
import edu.etc.karamach.xml.parser.XMLMenuParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class StAXMenuParser implements XMLMenuParser {
    private static final Logger logger = LogManager.getLogger("default");

    private static final String DASH_REGEX = "-";
    private static final String UNDERLINE_REPLACEMENT = "_";
    private static final String ID_ATTRIBUTE_NAME = "id";
    private static final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private XMLStreamReader xmlStreamReader;

    @Override
    public List<FoodMenu> parse(String inputXML) throws XMLParserException {

        try {
            InputStream fileInputStream = new FileInputStream(inputXML);

            xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

        } catch (FileNotFoundException | XMLStreamException e) {
            logger.error("Unable to initialize StAX parser");
            throw new StAXMenuParserException("Some thing goes wrong during initialization of StAX parser", e);
        }

        BreakfastMenu breakfastMenu = new BreakfastMenu();
        MainMenu mainMenu = new MainMenu();

        parse(breakfastMenu, mainMenu);

        List<FoodMenu> outputList = Arrays.asList(breakfastMenu, mainMenu);

        return outputList;
    }

    private void parse(BreakfastMenu breakfastMenu, MainMenu mainMenu) throws StAXMenuParserException {
        FoodMenu currentMenu = breakfastMenu;

        MenuTagName elementName = null;
        Food food = null;

        String localName;

        try {
            while (xmlStreamReader.hasNext()) {
                int type = xmlStreamReader.next();

                switch (type) {
                    case XMLStreamConstants.END_DOCUMENT:

                        currentMenu = null;

                        if (logger.isDebugEnabled()) {
                            logger.debug("End document parsing");
                        }

                        break;

                    case XMLStreamConstants.START_ELEMENT:
                        localName = xmlStreamReader.getLocalName().toUpperCase().replaceAll(
                                DASH_REGEX, UNDERLINE_REPLACEMENT);

                        elementName = MenuTagName.valueOf(localName);

                        switch (elementName) {
                            case MENU:

                                if (logger.isDebugEnabled()) {
                                    logger.debug("Start parsing document");
                                }

                                break;

                            case MAIN_MENU:
                                currentMenu = mainMenu;
                                break;

                            case BREAKFAST_MENU:
                                currentMenu = breakfastMenu;
                                break;

                            case FOOD:

                                food = new Food();

                                int id = Integer.parseInt(
                                        xmlStreamReader.getAttributeValue(null, ID_ATTRIBUTE_NAME));
                                food.setId(id);

                                break;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String text = xmlStreamReader.getText().trim();

                        if (text.isEmpty()) {
                            break;
                        }

                        makeActionOnCharacters(elementName, food, text);
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        localName = xmlStreamReader.getLocalName().toUpperCase().replaceAll(
                                DASH_REGEX, UNDERLINE_REPLACEMENT);

                        elementName = MenuTagName.valueOf(localName);

                        food = makeActionOnEndElement(currentMenu, elementName, food);
                        break;
                }
            }
        } catch (XMLStreamException e) {
            logger.error("Get an error during parsing XML via " + getClass().getSimpleName());
            throw new StAXMenuParserException(e);
        }
    }

    private Food makeActionOnEndElement(FoodMenu currentMenu, MenuTagName elementName, Food food) {
        switch (elementName) {
            case FOOD:
                currentMenu.addFood(food);

                if (logger.isTraceEnabled()) {
                    logger.trace("New " + food + ": added!");
                }

                food = null;
                break;
        }
        return food;
    }

    private void makeActionOnCharacters(MenuTagName elementName, Food food, String text) {
        switch (elementName) {
            case NAME:
                food.setName(text);
                break;

            case IMAGE:
                food.setImageURI(text);
                break;

            case PRICE:
                food.addPrice(Double.parseDouble(text));
                break;

            case DESCRIPTION:
                food.addDescription(text);
                break;

            case WEIGHT:
                food.setWeight(text);
                break;
        }
    }
}
