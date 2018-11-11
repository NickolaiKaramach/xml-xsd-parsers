package edu.etc.karamach.xml.exception;

public class XMLParserFactoryException extends Exception {
    private static final long serialVersionUID = -7155191492565972166L;

    public XMLParserFactoryException() {
        super();
    }

    public XMLParserFactoryException(String message) {
        super(message);
    }

    public XMLParserFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLParserFactoryException(Throwable cause) {
        super(cause);
    }
}
