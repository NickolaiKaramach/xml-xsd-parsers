package edu.etc.karamach.xml.exception;

public class DOMParserException extends Exception {
    private static final long serialVersionUID = 231232875574043081L;

    public DOMParserException() {
        super();
    }

    public DOMParserException(String message) {
        super(message);
    }

    public DOMParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DOMParserException(Throwable cause) {
        super(cause);
    }
}
