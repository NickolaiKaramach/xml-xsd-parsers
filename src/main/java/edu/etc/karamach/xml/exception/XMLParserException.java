package edu.etc.karamach.xml.exception;

public class XMLParserException extends Exception {
    private static final long serialVersionUID = -5483031537418893917L;

    public XMLParserException() {
        super();
    }

    public XMLParserException(String message) {
        super(message);
    }

    public XMLParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLParserException(Throwable cause) {
        super(cause);
    }
}
