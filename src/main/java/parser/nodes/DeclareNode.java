package parser.nodes;

public class DeclareNode implements DeclarationNode {

    private IdentifierNode identifier;

    private TypeNode type; // This should be improved in the future.

    public DeclareNode(IdentifierNode identifier, TypeNode type) {
        this.identifier = identifier;
        this.type = type;
    }
}
