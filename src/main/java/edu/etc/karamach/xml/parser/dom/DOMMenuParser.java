package edu.etc.karamach.xml.parser.dom;

import edu.etc.karamach.xml.entity.Food;
import edu.etc.karamach.xml.entity.FoodType;
import edu.etc.karamach.xml.entity.Menu.BreakfastMenu;
import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.Menu.MainMenu;
import edu.etc.karamach.xml.exception.DOMParserException;
import edu.etc.karamach.xml.exception.XMLParserException;
import edu.etc.karamach.xml.parser.XMLMenuParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMMenuParser implements XMLMenuParser {
    private static final Logger logger = LogManager.getLogger("default");

    private static final String BREAKFAST_MENU_TAG_NAME = "breakfast-menu";
    private static final String MAIN_MENU_TAG_NAME = "main-menu";
    private static final String DESCRIPTION_TAG_NAME = "description";
    private static final String PRICE_TAG_NAME = "price";
    private static final String WEIGHT_TAG_NAME = "weight";
    private static final String IMAGE_TAG_NAME = "image";
    private static final String NAME_TAG_NAME = "name";
    private static final String ID_TAG_NAME = "id";
    private static final String FOOD_TAG_NAME = "food";

    private static final int FIRST_ELEMENT_INDEX = 0;
    private static final String FOOD_TYPE_TAG_NAME = "food-type";

    private DOMParser domParser = new DOMParser();

    @Override
    public List<FoodMenu> parse(String inputXML) throws XMLParserException {
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        MainMenu mainMenu = new MainMenu();

        List<FoodMenu> outputList = new ArrayList<>();

        parse(inputXML, breakfastMenu, mainMenu);
        outputList.add(breakfastMenu);
        outputList.add(mainMenu);

        return outputList;
    }

    private void parse(String inputXML, BreakfastMenu breakfastMenu, MainMenu mainMenu) throws DOMParserException {

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Start document parsing");
            }

            domParser.parse(inputXML);

            Document document = domParser.getDocument();
            Element root = document.getDocumentElement();

            NodeList currentMenu = root.getElementsByTagName(BREAKFAST_MENU_TAG_NAME);
            addFood((Element) currentMenu.item(0), breakfastMenu);

            currentMenu = root.getElementsByTagName(MAIN_MENU_TAG_NAME);
            addFood((Element) currentMenu.item(0), mainMenu);

            if (logger.isDebugEnabled()) {
                logger.debug("End document parsing");
            }

        } catch (IOException | SAXException e) {
            logger.error("Error catch at DOM parsing process");
            throw new DOMParserException(e);
        }

    }

    private void addFood(Element menu, FoodMenu foodMenu) {

        NodeList foodList = menu.getElementsByTagName(FOOD_TAG_NAME);
        fillMenuWithFoodList(foodMenu, foodList);

    }

    private void fillMenuWithFoodList(FoodMenu foodMenu, NodeList foodList) {

        for (int i = 0; i < foodList.getLength(); i++) {

            Food food = new Food();
            Element foodElement = (Element) foodList.item(i);

            setFoodData(food, foodElement);

            if (logger.isTraceEnabled()) {
                logger.trace("Add new " + food + " to menu!");
            }
            foodMenu.addFood(food);
        }
    }

    private void setFoodData(Food food, Element foodElement) {

        String idAttribute = foodElement.getAttribute(ID_TAG_NAME);
        food.setId(Integer.parseInt(idAttribute));

        Node nameNode = foodElement.getElementsByTagName(NAME_TAG_NAME).item(FIRST_ELEMENT_INDEX);
        food.setName(nameNode.getTextContent());

        Node imageNode = foodElement.getElementsByTagName(IMAGE_TAG_NAME).item(FIRST_ELEMENT_INDEX);
        food.setImageURI(imageNode.getTextContent());


        NodeList foodTypeNodes = foodElement.getElementsByTagName(FOOD_TYPE_TAG_NAME);

        for (int j = 0; j < foodTypeNodes.getLength(); j++) {

            FoodType currentFoodType = new FoodType();
            Element currentFoodTypeElement = (Element) foodTypeNodes.item(j);


            Node weightNode =
                    currentFoodTypeElement.getElementsByTagName(WEIGHT_TAG_NAME).item(FIRST_ELEMENT_INDEX);

            currentFoodType.setWeight(weightNode.getTextContent());


            Node priceNode =
                    currentFoodTypeElement.getElementsByTagName(PRICE_TAG_NAME).item(FIRST_ELEMENT_INDEX);

            currentFoodType.setPrice(Double.valueOf(priceNode.getTextContent()));


            Node descriptionNode =
                    currentFoodTypeElement.getElementsByTagName(DESCRIPTION_TAG_NAME).item(FIRST_ELEMENT_INDEX);

            currentFoodType.setDescription(descriptionNode.getTextContent());

            food.addFoodType(currentFoodType);
        }

    }

}
