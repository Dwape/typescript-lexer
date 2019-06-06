package parser.nodes;

import parser.NodeVisitor;

public class AssignmentNode implements StatementNode {

    private ExpressionNode expression;

    private IdentifierNode identifier;

    public AssignmentNode(IdentifierNode identifier, ExpressionNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAssignmentNode(this);
    }

    public ExpressionNode getExpression() {
        return expression;
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }
}
