package parser.nodes;

public interface DeclarationNode extends StatementNode {

    void accept(NodeVisitor visitor);
}
