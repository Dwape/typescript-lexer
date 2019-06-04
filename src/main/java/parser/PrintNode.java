package parser;

import parser.nodes.ExpressionNode;
import parser.nodes.StatementNode;

public class PrintNode implements StatementNode {

    private ExpressionNode expression; // I will probably need to add a getter here (As in every node).

    public PrintNode(ExpressionNode expression) {
        this.expression = expression;
    }
}
