package parser.nodes;

import parser.NodeVisitor;

public class MultiplicationNode implements TermNode{

    private TermNode left; // This value can be null

    private LiteralNode right;

    public MultiplicationNode(TermNode left, LiteralNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitMultiplicationNode(this);
    }

    public TermNode getLeft() {
        return left;
    }

    public LiteralNode getRight() {
        return right;
    }
}
