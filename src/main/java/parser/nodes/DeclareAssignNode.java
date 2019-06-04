package parser.nodes;

import parser.NodeVisitor;

public class DeclareAssignNode implements DeclarationNode {

    private ExpressionNode expression;

    private IdentifierNode identifier;

    private String type; // Types can be defined as strings.

    public DeclareAssignNode(IdentifierNode identifier, String type, ExpressionNode expression) {
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visitDeclareAssignNode(this);
    }
}
