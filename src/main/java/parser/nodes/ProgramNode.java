package parser.nodes;

import parser.NodeVisitor;

public interface ProgramNode extends ASTNode {

    public void addStatement(StatementNode node);

    void accept(NodeVisitor visitor);
}
