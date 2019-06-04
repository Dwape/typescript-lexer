package parser.nodes;

public class TermSingleNode implements TermNode {

    private LiteralNode term;

    public TermSingleNode(LiteralNode term) {
        this.term = term;
    }
}
