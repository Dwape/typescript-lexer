package parser.nodes;

public class ExpressionSingleNode implements ExpressionNode {

    private TermNode term;

    public ExpressionSingleNode(TermNode term) {
        this.term = term;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitExpressionSingleNode(this);
    }

    public TermNode getTerm() {
        return term;
    }
}
