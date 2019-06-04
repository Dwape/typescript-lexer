package parser.nodes;

import parser.NodeVisitor;

public class StringNode implements LiteralNode{

    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visitStringNode(this);
    }
}
