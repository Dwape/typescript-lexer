package parser.nodes;

import parser.NodeVisitor;

public class NumberNode implements LiteralNode{

    private String value;

    public NumberNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visitNumberNode(this);
    }
}
