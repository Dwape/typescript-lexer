package parser;

public class SyntaxErrorException extends RuntimeException {

    public SyntaxErrorException(String errorMessage) {
        super(errorMessage);
    }
}
