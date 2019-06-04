package parser;

public class AdditionNode implements ExpressionNode{

    private ExpressionNode left; // This value can be null

    private TermNode right;

    public AdditionNode(ExpressionNode left, TermNode right) {
        this.left = left;
        this.right = right;
    }
}
