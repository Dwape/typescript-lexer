package parser.nodes;

import java.util.ArrayList;
import java.util.List;

// This name is lame, try to change it.
public class TreeNode implements ProgramNode{

    // All the statements in the program, in order of execution.
    private List<StatementNode> statements;

    public TreeNode() {
        this.statements = new ArrayList<>();
    }

    // Adds a statement.
    // Should this method be in ProgramNode?
    public void addStatement(StatementNode node) {
        statements.add(node);
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitTreeNode(this);
    }

    public List<StatementNode> getStatements() {
        return statements;
    }
}
