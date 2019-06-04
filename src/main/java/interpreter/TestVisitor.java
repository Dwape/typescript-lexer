package interpreter;

import parser.NodeVisitor;
import parser.nodes.*;

import java.util.List;
import java.util.Stack;

public class TestVisitor implements NodeVisitor {

    // We should store some state somewhere
    // What is the best way of doing this?

    // We should also have a map with the variables, their types and their value.

    // Some variable with a value and a type, right?
    // Let's try if it works with Strings to begin with.
    private Stack<String> magicStack; // Change this name

    public TestVisitor() {
        this.magicStack = new Stack<>();
    }

    public void start(ASTNode node) {
        node.visit(this);
    }

    @Override
    public void visitAdditionNode(AdditionNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        String value1 = magicStack.pop();
        String value2 = magicStack.pop();
        // We have to consider concatenation here
        // But we still don't have types.
        magicStack.push(Double.toString(Double.parseDouble(value1)+Double.parseDouble(value2)));
    }

    @Override
    public void visitAssignmentNode(AssignmentNode node) {

    }

    @Override
    public void visitDeclareAssignNode(DeclareAssignNode node) {

    }

    @Override
    public void visitDeclareNode(DeclareNode node) {

    }

    @Override
    public void visitDivisionNode(DivisionNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        String value1 = magicStack.pop();
        String value2 = magicStack.pop();
        magicStack.push(Double.toString(Double.parseDouble(value1)/Double.parseDouble(value2)));
    }

    @Override
    public void visitExpressionSingleNode(ExpressionSingleNode node) {
        node.getTerm().visit(this);
    }

    @Override
    public void visitIdentifierNode(IdentifierNode node) {

    }

    @Override
    public void visitMultiplicationNode(MultiplicationNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        String value1 = magicStack.pop();
        String value2 = magicStack.pop();
        magicStack.push(Double.toString(Double.parseDouble(value1)*Double.parseDouble(value2)));
    }

    @Override
    public void visitNumberNode(NumberNode node) {
        magicStack.push(node.getValue());
    }

    // It will probably be a good idea to create an abstraction for the console.
    // It could be an interface that can have different standard outs, although the default will be console.
    @Override
    public void visitPrintNode(PrintNode node) {
        // How would the visiting logic even work?
        // We are calling a method on the visitor which is not very useful to say the least
        // Maybe if the one reading the values had a state it could work
        // But we still need to think a little more about this.

        // We need the expression to be saved somewhere.
        node.getExpression().visit(this);
        // We print after we are sure the value is in the "stack"
        String value = magicStack.pop(); // When used, we should remove it.
        System.out.println(value);
    }

    @Override
    // Even if we returned the value, the node would receive it
    // Could the visit method return the value?
    // It would have to be a generic type for all the different nodes.
    // Maybe a node visit response?
    public void visitStringNode(StringNode node) {
        // What should we do with this value?
        // Should we write it somewhere and then read it?
        // Or could we try to return it?
        node.getValue();
    }

    @Override
    public void visitSubtractionNode(SubtractionNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        String value1 = magicStack.pop();
        String value2 = magicStack.pop();
        magicStack.push(Double.toString(Double.parseDouble(value1)-Double.parseDouble(value2)));
    }

    @Override
    public void visitTermSingleNode(TermSingleNode node) {
        node.getTerm().visit(this);
    }

    @Override
    public void visitTreeNode(TreeNode node) {
        // Visit all statements, in order
        List<StatementNode> statements = node.getStatements();
        for (StatementNode statement : statements) {
            statement.visit(this);
        }
    }
}
