package parser.nodes;

public class NumberNode implements LiteralNode{

    private String value;

    public NumberNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitNumberNode(this);
    }
}
