package edu.etc.karamach.xml.sax.handler;

import edu.etc.karamach.xml.entity.Food;
import edu.etc.karamach.xml.entity.Menu.BreakfastMenu;
import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.Menu.MainMenu;
import edu.etc.karamach.xml.entity.MenuTagName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXMenuHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger("default");

    private MainMenu mainMenu = new MainMenu();
    private BreakfastMenu breakfastMenu = new BreakfastMenu();
    private FoodMenu currentMenu;

    private Food food;
    private StringBuilder text;

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public BreakfastMenu getBreakfastMenu() {
        return breakfastMenu;
    }

    @Override
    public void startDocument() throws SAXException {
        logger.debug("Start document parsing");
    }

    @Override
    public void endDocument() throws SAXException {
        currentMenu = null;
        text = null;
        logger.debug("End document parsing");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();

        if (!qName.contains(":")) {

            MenuTagName tagName =
                    MenuTagName.valueOf(qName.toUpperCase().replaceAll("-", "_"));

            switch (tagName) {
                case FOOD:
                    food = new Food();
                    food.setId(Integer.parseInt(attributes.getValue("id")));
                    break;
                case BREAKFAST_MENU:
                    currentMenu = breakfastMenu;
                    break;
                case MAIN_MENU:
                    currentMenu = mainMenu;
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!qName.contains(":")) {
            MenuTagName tagName =
                    MenuTagName.valueOf(qName.toUpperCase().replaceAll("-", "_"));
            switch (tagName) {
                case NAME:
                    food.setName(text.toString());
                    break;
                case IMAGE:
                    food.setImageURI(text.toString());
                    break;
                case PRICE:
                    food.addPrice(Double.parseDouble(text.toString()));
                    break;
                case DESCRIPTION:
                    food.addDescription(text.toString());
                    break;
                case WEIGHT:
                    food.setWeight(text.toString());
                    break;
                case FOOD:
                    currentMenu.addFood(food);
                    logger.trace("New " + food + ": added!");
                    food = null;
                    break;
            }
        }

    }
}
