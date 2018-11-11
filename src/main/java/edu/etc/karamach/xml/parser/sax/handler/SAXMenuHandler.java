package edu.etc.karamach.xml.parser.sax.handler;

import edu.etc.karamach.xml.entity.Food;
import edu.etc.karamach.xml.entity.Menu.BreakfastMenu;
import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.Menu.MainMenu;
import edu.etc.karamach.xml.entity.MenuTagName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class SAXMenuHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger("default");

    private static final String COLON = ":";
    private static final String DASH_REGEX = "-";
    private static final String UNDERLINE_REPLACEMENT = "_";
    private static final String ID_ATTRIBUTE_NAME = "id";

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
    public void startDocument() {
        if (logger.isDebugEnabled()) {
            logger.debug("Start document parsing");
        }
    }

    @Override
    public void endDocument() {
        currentMenu = null;
        text = null;
        if (logger.isDebugEnabled()) {
            logger.debug("End document parsing");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        text = new StringBuilder();

        if (!qName.contains(COLON)) {

            MenuTagName tagName = getMenuTagName(qName);

            switch (tagName) {
                case FOOD:
                    food = new Food();
                    food.setId(Integer.parseInt(attributes.getValue(ID_ATTRIBUTE_NAME)));
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
    public void endElement(String uri, String localName, String qName) {
        if (!qName.contains(COLON)) {

            MenuTagName tagName = getMenuTagName(qName);

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

                    if (logger.isTraceEnabled()) {
                        logger.trace("New " + food + ": added!");
                    }

                    food = null;
                    break;
            }
        }

    }

    private MenuTagName getMenuTagName(String qName) {
        return MenuTagName.valueOf(qName.toUpperCase().replaceAll(DASH_REGEX, UNDERLINE_REPLACEMENT));
    }
}
