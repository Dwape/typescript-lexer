package interpreter;

public class VariableFactory {

    // This may not be necessary.
    // Or it could be somewhere else, like a variable factory or something.
    // The variable factory could be a good idea actually.
    public Variable createVariable(String value, String type) {
        if (type.equals("number")) {
            return new NumberVariable(value);
        } else { // It should be a string.
            return new StringVariable(value);
        }
    }
}
