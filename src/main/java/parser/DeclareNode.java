package parser;

public class DeclareNode implements DeclarationNode {

    private IdentifierNode identifier;

    private String type; // This should be improved in the future.

    public DeclareNode(IdentifierNode identifier, String type) {
        this.identifier = identifier;
        this.type = type;
    }
}
