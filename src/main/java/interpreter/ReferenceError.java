package interpreter;

public class ReferenceError extends RuntimeException {

    public ReferenceError(String errorMessage) {
        super(errorMessage);
    }

}
