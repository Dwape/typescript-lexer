package parser.nodes;

import parser.NodeVisitor;

public class SubtractionNode implements ExpressionNode {

    private ExpressionNode left; // This value can be null

    private TermNode right;

    public SubtractionNode(ExpressionNode left, TermNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visitSubtractionNode(this);
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public TermNode getRight() {
        return right;
    }
}
