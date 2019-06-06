package interpreter;

public interface Variable {

    public Variable add(Variable variable);

    public Variable subtract(Variable variable);

    public Variable multiply(Variable variable);

    public Variable divide(Variable variable);

    public String getType();

    public String getValue();
}
