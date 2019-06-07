package parser.nodes;

import parser.nodes.*;

// Visits Nodes to do something with them depending on their type
// It is basically the Visitor Pattern.
public interface NodeVisitor {

    void visitAdditionNode(AdditionNode node);

    void visitAssignmentNode(AssignmentNode node);

    void visitDeclareAssignNode(DeclareAssignNode node);

    void visitDeclareNode(DeclareNode node);

    void visitDivisionNode(DivisionNode node);

    // ExpressionSingleNode and TermSingleNode should do the same thing
    void visitExpressionSingleNode(ExpressionSingleNode node);

    void visitIdentifierNode(IdentifierNode node);

    void visitMultiplicationNode(MultiplicationNode node);

    void visitNumberNode(NumberNode node);

    void visitPrintNode(PrintNode node);

    void visitStringNode(StringNode node);

    void visitSubtractionNode(SubtractionNode node);

    // ExpressionSingleNode and TermSingleNode should do the same thing
    void visitTermSingleNode(TermSingleNode node);

    void visitTreeNode(TreeNode node);
}
