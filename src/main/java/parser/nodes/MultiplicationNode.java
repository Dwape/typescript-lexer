package parser.nodes;

public class MultiplicationNode implements TermNode{

    private TermNode left; // This value can be null

    private LiteralNode right;

    public MultiplicationNode(TermNode left, LiteralNode right) {
        this.left = left;
        this.right = right;
    }
}
