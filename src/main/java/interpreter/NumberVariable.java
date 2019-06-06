package interpreter;

public class NumberVariable implements Variable {

    // It could also be stored as a double.
    private String value; // For now we'll keep this as a string (until a better solution is found).

    public NumberVariable(String value) {
        this.value = value;
    }

    @Override
    public Variable add(Variable variable) {
        // Here we must consider both addition and concatenation.
        if (variable.getType().equals("string")) {
            return new StringVariable(value + variable.getValue());
        }
        // This needs to be improved in some way.
        double value1 = Double.parseDouble(value);
        double value2 = Double.parseDouble(variable.getValue());
        String result = Double.toString(value1+value2);

        return new NumberVariable(result);
    }

    @Override
    public Variable subtract(Variable variable) {
        // This behavior will be repeated (could be extracted to another method).
        if (variable.getType().equals("string")) {
            throw new TypeError(); // add some text. (maybe)
        }
        // We need the internal value of the variable, how should it be saved?
        // If we send it as a string, then we have to convert it twice (which is less than convenient).

        // This is horrible, how can it be improved?
        double value1 = Double.parseDouble(value);
        double value2 = Double.parseDouble(variable.getValue());
        String result = Double.toString(value1-value2);

        return new NumberVariable(result);
    }

    @Override
    public Variable multiply(Variable variable) {
        if (variable.getType().equals("string")) {
            throw new TypeError(); // add some text. (maybe)
        }
        double value1 = Double.parseDouble(value);
        double value2 = Double.parseDouble(variable.getValue());
        String result = Double.toString(value1*value2);

        return new NumberVariable(result);
    }

    @Override
    public Variable divide(Variable variable) {
        if (variable.getType().equals("string")) {
            throw new TypeError(); // add some text. (maybe)
        }
        double value1 = Double.parseDouble(value);
        double value2 = Double.parseDouble(variable.getValue());
        String result = Double.toString(value1/value2);

        return new NumberVariable(result);
    }

    @Override
    public String getType() {
        return "number";
    }

    @Override
    public String getValue() {
        return value;
    }
}
