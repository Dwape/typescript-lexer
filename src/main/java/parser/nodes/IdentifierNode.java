package parser.nodes;

import parser.NodeVisitor;

public class IdentifierNode implements LiteralNode{

    private String name;

    public IdentifierNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitIdentifierNode(this);
    }
}
