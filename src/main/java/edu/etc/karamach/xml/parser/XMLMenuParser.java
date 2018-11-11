package edu.etc.karamach.xml.parser;

import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.exception.XMLParserException;

import java.util.List;

public interface XMLMenuParser {
    List<FoodMenu> parse(String inputXML) throws XMLParserException;
}
