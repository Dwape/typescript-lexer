package parser.nodes;

public interface ExpressionNode extends ASTNode{

    void accept(NodeVisitor visitor);
}
