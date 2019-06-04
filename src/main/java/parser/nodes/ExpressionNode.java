package parser.nodes;

import parser.NodeVisitor;

public interface ExpressionNode extends ASTNode{

    void visit(NodeVisitor visitor);
}
