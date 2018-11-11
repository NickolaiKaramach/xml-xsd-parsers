package edu.etc.karamach.xml.parser.sax;

import edu.etc.karamach.xml.entity.Menu.FoodMenu;
import edu.etc.karamach.xml.exception.SAXMenuParserException;
import edu.etc.karamach.xml.parser.XMLMenuParser;
import edu.etc.karamach.xml.parser.sax.handler.SAXMenuHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXMenuParser implements XMLMenuParser {
    private XMLReader reader;

    private SAXMenuHandler handler = new SAXMenuHandler();

    public SAXMenuParser(XMLReader reader) {
        this.reader = reader;
        reader.setContentHandler(handler);
    }

    @Override
    public List<FoodMenu> parse(String inputXML) throws SAXMenuParserException {
        doParse(inputXML);

        List<FoodMenu> outputList = new ArrayList<>();

        outputList.add(handler.getBreakfastMenu());
        outputList.add(handler.getMainMenu());

        return outputList;
    }

    private void doParse(String uri) throws SAXMenuParserException {
        InputSource inputSource = new InputSource(uri);

        try {

            reader.parse(inputSource);

        } catch (IOException | SAXException e) {
            throw new SAXMenuParserException(e);
        }
    }
}
