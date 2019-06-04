package parser;

public class SubtractionNode implements ExpressionNode {

    private ExpressionNode left; // This value can be null

    private TermNode right;

    public SubtractionNode(ExpressionNode left, TermNode right) {
        this.left = left;
        this.right = right;
    }
}
