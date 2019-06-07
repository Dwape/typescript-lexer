package parser.nodes;

public interface ProgramNode extends ASTNode {

    public void addStatement(StatementNode node);

    void accept(NodeVisitor visitor);
}
