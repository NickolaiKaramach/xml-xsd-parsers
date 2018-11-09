package edu.etc.karamach.xml.stax;

import edu.etc.karamach.xml.entity.Food;
import edu.etc.karamach.xml.entity.Menu.BreakfastMenu;
import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.Menu.MainMenu;
import edu.etc.karamach.xml.entity.MenuTagName;
import edu.etc.karamach.xml.exception.StAXMenuParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXMenuParser {
    private static final Logger logger = LogManager.getLogger("default");

    XMLStreamReader xmlStreamReader;

    public StAXMenuParser(XMLStreamReader xmlStreamReader) {
        setXmlStreamReader(xmlStreamReader);
    }

    public void setXmlStreamReader(XMLStreamReader xmlStreamReader) {
        this.xmlStreamReader = xmlStreamReader;
    }

    public void parse(BreakfastMenu breakfastMenu, MainMenu mainMenu) throws StAXMenuParserException {
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
                        logger.debug("End document parsing");
                        break;

                    case XMLStreamConstants.START_ELEMENT:
                        localName = xmlStreamReader.getLocalName().toUpperCase().replaceAll("-", "_");
                        elementName = MenuTagName.valueOf(localName);

                        switch (elementName) {
                            case MENU:
                                logger.debug("Start parsing document");
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
                                        xmlStreamReader.getAttributeValue(null, "id"));
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
                        localName = xmlStreamReader.getLocalName().toUpperCase().replaceAll("-", "_");
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
                logger.trace("New " + food + ": added!");

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
