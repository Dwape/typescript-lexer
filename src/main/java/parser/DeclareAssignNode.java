package parser;

public class DeclareAssignNode implements DeclarationNode {

    private ExpressionNode expression;

    private IdentifierNode identifier;

    private TypeNode type; // This should be improved in the future.

    public DeclareAssignNode(IdentifierNode identifier, TypeNode type, ExpressionNode expression) {
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }
}
