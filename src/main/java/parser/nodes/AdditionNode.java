package parser.nodes;

public class AdditionNode implements ExpressionNode {

    private ExpressionNode left; // This value can be null

    private TermNode right;

    public AdditionNode(ExpressionNode left, TermNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAdditionNode(this);
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public TermNode getRight() {
        return right;
    }
}
