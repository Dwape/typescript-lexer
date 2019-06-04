package parser;

public class IdentifierNode implements LiteralNode{

    private String name;

    public IdentifierNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
