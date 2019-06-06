package interpreter;

public class TypeError extends RuntimeException {

    public TypeError(String errorMessage) {
        super(errorMessage);
    }
}
