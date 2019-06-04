package parser.nodes;

import parser.NodeVisitor;

public interface DeclarationNode extends StatementNode {

    void visit(NodeVisitor visitor);
}
