package interpreter;

import parser.NodeVisitor;
import parser.nodes.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TestVisitor implements NodeVisitor {

    // We should store some state somewhere
    // What is the best way of doing this?

    // We should also have a map with the variables, their types and their value.

    // Some variable with a value and a type, right?
    // Let's try if it works with Strings to begin with.

    // Using variables could be better
    private Stack<Variable> magicStack; // Change this name
    private Map<String, Variable> context; // This holds all the variables.

    public TestVisitor() {
        this.magicStack = new Stack<>();
        this.context = new HashMap<>();
    }

    public void start(ASTNode node) {
        node.visit(this);
    }

    @Override
    public void visitAdditionNode(AdditionNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        Variable value1 = magicStack.pop();
        Variable value2 = magicStack.pop();
        // We have to consider concatenation here
        // But we still don't have types.
        if (value1.getType().equals("string") || value2.getType().equals("string")) {
            // If this happens, then we have a string concatenation
            Variable newVariable = new Variable(value1.getValue() + value2.getValue(), "string");
            magicStack.push(newVariable);
        } else {
            String value = Double.toString(Double.parseDouble(value1.getValue())+Double.parseDouble(value2.getValue()));
            Variable newVariable = new Variable(value, "number");
            magicStack.push(newVariable);
        }
    }

    @Override
    public void visitAssignmentNode(AssignmentNode node) {
        node.getExpression().visit(this);
        String name = node.getIdentifier().getName();
        Variable variable = context.get(name);
        if (variable == null) {
            // throw an exception
            // Is this reference error necessary?
            throw new ReferenceError(); // We could add some text here (to say which variable failed).
            // For example, ${name} is not defined.
        }
        // We need to check types here.
        Variable newVariable = magicStack.pop();
        if (!newVariable.getType().equals(variable.getType())) {
            // throw an exception
            throw new TypeError(); // We could add some text here (to say which variable failed).
        }
        variable = new Variable(newVariable.getValue(), variable.getType());
        context.put(name, variable);
    }

    @Override
    public void visitDeclareAssignNode(DeclareAssignNode node) {
        node.getExpression().visit(this);
        String name = node.getIdentifier().getName();

        Variable newVariable = magicStack.pop();
        if (!newVariable.getType().equals(node.getType())) {
            // throw an exception
            throw new TypeError(); // We could add some text here (to say which variable failed).
        }
        Variable variable = new Variable(newVariable.getValue(), node.getType());
        context.put(name, variable);
    }

    @Override
    public void visitDeclareNode(DeclareNode node) {
        // We are creating a variable without a value, how should this work?
        String name = node.getIdentifier().getName();
        // Sometimes there is no value in the stack.

        Variable variable;
        if (magicStack.isEmpty()) {
            variable = new Variable(null, node.getType());
        } else {
            Variable value = magicStack.pop();
            variable = new Variable(value.getValue(), node.getType());
        }
        // It doesn't have a value!
        // What should happen?
        context.put(name, variable);
    }

    @Override
    public void visitDivisionNode(DivisionNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        Variable value1 = magicStack.pop();
        Variable value2 = magicStack.pop();
        if (value1.getType().equals("string") || value2.getType().equals("string")) {
            // Exception
        }
        String value = Double.toString(Double.parseDouble(value1.getValue())/Double.parseDouble(value2.getValue()));
        Variable newVariable = new Variable(value, "number");
        magicStack.push(newVariable);
    }

    @Override
    public void visitExpressionSingleNode(ExpressionSingleNode node) {
        node.getTerm().visit(this);
    }

    @Override
    public void visitIdentifierNode(IdentifierNode node) {
        String name = node.getName();
        Variable variable = context.get(name);
        if (variable == null) {
            throw new ReferenceError();
        }
        // This might be null, exception.
        magicStack.push(variable);
    }

    @Override
    public void visitMultiplicationNode(MultiplicationNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        Variable value1 = magicStack.pop();
        Variable value2 = magicStack.pop();
        if (value1.getType().equals("string") || value2.getType().equals("string")) {
            // Exception
        }
        String value = Double.toString(Double.parseDouble(value1.getValue())*Double.parseDouble(value2.getValue()));
        Variable newVariable = new Variable(value, "number");
        magicStack.push(newVariable);
    }

    @Override
    public void visitNumberNode(NumberNode node) {
        Variable variable = new Variable(node.getValue(), "number");
        magicStack.push(variable);
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
        String value = magicStack.pop().getValue(); // When used, we should remove it.
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
        Variable variable = new Variable(node.getValue(), "string");
        magicStack.push(variable);
    }

    @Override
    public void visitSubtractionNode(SubtractionNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        Variable value1 = magicStack.pop();
        Variable value2 = magicStack.pop();
        if (value1.getType().equals("string") || value2.getType().equals("string")) {
            // Exception
        }
        String value = Double.toString(Double.parseDouble(value1.getValue())-Double.parseDouble(value2.getValue()));
        Variable newVariable = new Variable(value, "number");
        magicStack.push(newVariable);
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
