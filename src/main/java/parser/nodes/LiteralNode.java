package parser.nodes;

import parser.NodeVisitor;

public interface LiteralNode extends ASTNode{

    void visit(NodeVisitor visitor);
}
