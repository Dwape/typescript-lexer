package parser.nodes;

public class DivisionNode implements TermNode{

    private TermNode left; // This value can be null

    private LiteralNode right;

    public DivisionNode(TermNode left, LiteralNode right) {
        this.left = left;
        this.right = right;
    }
}
