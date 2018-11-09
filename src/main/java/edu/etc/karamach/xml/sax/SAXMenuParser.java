package edu.etc.karamach.xml.sax;

import edu.etc.karamach.xml.exception.SAXMenuParserException;
import edu.etc.karamach.xml.sax.handler.SAXMenuHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;

public class SAXMenuParser {
    private XMLReader reader;
    private SAXMenuHandler handler = new SAXMenuHandler();

    public SAXMenuParser(XMLReader reader) {
        this.reader = reader;
        reader.setContentHandler(handler);
    }

    public void parse(String uri) throws SAXMenuParserException {
        InputSource inputSource = new InputSource(uri);

        try {

            reader.parse(inputSource);

        } catch (IOException | SAXException e) {
            throw new SAXMenuParserException(e);
        }
    }

    public SAXMenuHandler getHandler() {
        return handler;
    }

}
