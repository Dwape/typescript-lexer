package interpreter;

public class StringVariable implements Variable {

    private String value;

    public StringVariable(String value) {
        this.value = value;
    }

    @Override
    public Variable add(Variable variable) {
        return new StringVariable(value + variable.getValue());
    }

    @Override
    public Variable subtract(Variable variable) {
        throw new TypeError("Can't subtract a string");
    }

    @Override
    public Variable multiply(Variable variable) {
        throw new TypeError("Can't multiply a string");
    }

    @Override
    public Variable divide(Variable variable) {
        throw new TypeError("Can't divide a string");
    }

    @Override
    public String getType() {
        return "string";
    }

    @Override
    public String getValue() {
        return value;
    }
}
