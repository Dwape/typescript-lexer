package interpreter;

// This could be an interface, if that was necessary.
public class Variable {

    // Should this be already converted?
    // If we know the type then there is no real problem here
    private String value;

    private String type;

    // Should the name go here or stored in the map.
    public Variable(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
