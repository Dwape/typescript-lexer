package parser;

public class StringNode implements LiteralNode{

    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
