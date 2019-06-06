package parser.nodes;

import parser.NodeVisitor;
import parser.nodes.ExpressionNode;
import parser.nodes.StatementNode;

public class PrintNode implements StatementNode {

    private ExpressionNode expression; // I will probably need to add a getter here (As in every node).

    public PrintNode(ExpressionNode expression) {
        this.expression = expression;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitPrintNode(this);
    }

    public ExpressionNode getExpression() {
        return expression;
    }
}
