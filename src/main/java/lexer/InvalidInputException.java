package lexer;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidInputException() {}
}
