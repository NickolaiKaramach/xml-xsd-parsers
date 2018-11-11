package edu.etc.karamach.xml.factory;

import edu.etc.karamach.xml.entity.XMLParserType;
import edu.etc.karamach.xml.exception.XMLParserFactoryException;
import edu.etc.karamach.xml.parser.XMLMenuParser;
import edu.etc.karamach.xml.parser.dom.DOMMenuParser;
import edu.etc.karamach.xml.parser.sax.SAXMenuParser;
import edu.etc.karamach.xml.parser.stax.StAXMenuParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLMenuParserFactory {
    private static final Logger logger = LogManager.getLogger("default");
    private static XMLMenuParserFactory factory;

    private XMLMenuParserFactory() {

    }

    public static XMLMenuParserFactory getInstance() {

        if (factory == null) {
            factory = new XMLMenuParserFactory();
        }

        return factory;
    }

    public XMLMenuParser getXMLParser(XMLParserType type) throws XMLParserFactoryException {
        try {
            switch (type) {
                case DOM:
                    return new DOMMenuParser();
                case SAX:
                    XMLReader reader = XMLReaderFactory.createXMLReader();
                    return new SAXMenuParser(reader);
                case STAX:
                    return new StAXMenuParser();
                default:
                    logger.error("Invalid input for creating new Parser");
                    throw new XMLParserFactoryException();
            }
        } catch (SAXException e) {
            logger.error("Unable to create new Parser");
            throw new XMLParserFactoryException();
        }
    }
}
