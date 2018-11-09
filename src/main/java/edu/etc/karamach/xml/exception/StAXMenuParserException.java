package edu.etc.karamach.xml.exception;

public class StAXMenuParserException extends Exception {
    private static final long serialVersionUID = 1009779098417557795L;

    public StAXMenuParserException() {
        super();
    }

    public StAXMenuParserException(String message) {
        super(message);
    }

    public StAXMenuParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public StAXMenuParserException(Throwable cause) {
        super(cause);
    }
}
