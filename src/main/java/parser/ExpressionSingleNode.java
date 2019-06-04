package parser;

public class ExpressionSingleNode implements ExpressionNode {

    private TermNode term;

    public ExpressionSingleNode(TermNode term) {
        this.term = term;
    }
}
