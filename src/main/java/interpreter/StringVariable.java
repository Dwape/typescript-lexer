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
        throw new TypeError();
    }

    @Override
    public Variable multiply(Variable variable) {
        throw new TypeError();
    }

    @Override
    public Variable divide(Variable variable) {
        throw new TypeError();
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
