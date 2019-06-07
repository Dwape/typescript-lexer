package parser.nodes;

public class TermSingleNode implements TermNode {

    private LiteralNode term;

    public TermSingleNode(LiteralNode term) {
        this.term = term;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitTermSingleNode(this);
    }

    public LiteralNode getTerm() {
        return term;
    }
}
