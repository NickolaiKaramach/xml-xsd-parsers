package edu.etc.karamach.xml.dom;

import edu.etc.karamach.xml.entity.Food;
import edu.etc.karamach.xml.entity.Menu.BreakfastMenu;
import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.entity.Menu.MainMenu;
import edu.etc.karamach.xml.exception.DOMParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

public class DOMMenuParser {
    private static final Logger logger = LogManager.getLogger("default");
    private DOMParser domParser = new DOMParser();

    public void parse(String inputXML, BreakfastMenu breakfastMenu, MainMenu mainMenu) throws DOMParserException {

        try {
            logger.debug("Start document parsing");
            domParser.parse(inputXML);

            Document document = domParser.getDocument();
            Element root = document.getDocumentElement();

            //TODO:QUESTION: Extract literal to constant?
            NodeList currentMenu = root.getElementsByTagName("breakfast-menu");
            addFood((Element) currentMenu.item(0), breakfastMenu);

            currentMenu = root.getElementsByTagName("main-menu");
            addFood((Element) currentMenu.item(0), mainMenu);

            logger.debug("End document parsing");
        } catch (IOException | SAXException e) {
            logger.error("Error catch at DOM parsing process");
            throw new DOMParserException(e);
        }

    }

    private void addFood(Element menu, FoodMenu foodMenu) {

        NodeList foodList = menu.getElementsByTagName("food");
        fillMenuWithFoodList(foodMenu, foodList);

    }

    private void fillMenuWithFoodList(FoodMenu foodMenu, NodeList foodList) {

        for (int i = 0; i < foodList.getLength(); i++) {

            Food food = new Food();
            Element foodElement = (Element) foodList.item(i);

            setFoodData(food, foodElement);

            logger.trace("Add new " + food + " to menu!");
            foodMenu.addFood(food);
        }
    }

    private void setFoodData(Food food, Element foodElement) {
        String idAttribute = foodElement.getAttribute("id");
        food.setId(Integer.parseInt(idAttribute));

        Node nameNode = foodElement.getElementsByTagName("name").item(0);
        food.setName(nameNode.getTextContent());

        Node imageNode = foodElement.getElementsByTagName("image").item(0);
        food.setImageURI(imageNode.getTextContent());

        Node weightNode = foodElement.getElementsByTagName("weight").item(0);
        food.setWeight(weightNode.getTextContent());


        NodeList priceNodes = foodElement.getElementsByTagName("price");

        for (int j = 0; j < priceNodes.getLength(); j++) {

            Node currentPrice = priceNodes.item(j);
            food.addPrice(Double.parseDouble(currentPrice.getTextContent()));

        }


        NodeList descriptionNodes = foodElement.getElementsByTagName("description");

        for (int j = 0; j < descriptionNodes.getLength(); j++) {

            Node currentDescription = descriptionNodes.item(j);
            food.addDescription(currentDescription.getTextContent());

        }
    }
}
