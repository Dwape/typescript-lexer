package parser.nodes;

public interface LiteralNode extends ASTNode{

    void accept(NodeVisitor visitor);
}
