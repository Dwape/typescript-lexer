package parser;

public class AssignmentNode implements StatementNode {

    private ExpressionNode expression;

    private IdentifierNode identifier;

    public AssignmentNode(IdentifierNode identifier, ExpressionNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }
}
