package interpreter;

import parser.NodeVisitor;
import parser.nodes.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class InterpreterVisitor implements NodeVisitor {

    // Using variables could be better
    private Stack<Variable> magicStack; // Change this name
    private Map<String, Variable> context; // This holds all the variables.
    private VariableFactory factory;
    private Output output;

    public InterpreterVisitor() {
        this.magicStack = new Stack<>();
        this.context = new HashMap<>();
        this.factory = new VariableFactory();
        this.output = new Console();
    }

    public void start(ASTNode node) {
        node.visit(this);
    }

    @Override
    public void visitAdditionNode(AdditionNode node) {
        // This is repeated many times, how can we generalize it?
        node.getRight().visit(this);
        node.getLeft().visit(this);
        Variable value1 = magicStack.pop();
        Variable value2 = magicStack.pop();

        magicStack.push(value1.add(value2));
    }

    @Override
    public void visitAssignmentNode(AssignmentNode node) {
        node.getExpression().visit(this);
        String name = node.getIdentifier().getName();
        if (!context.containsKey(name)) {
            // throw an exception
            // Is this reference error necessary?
            throw new ReferenceError(String.format("%s is not defined", name)); // We could add some text here (to say which variable failed).
            // For example, ${name} is not defined.
        }
        Variable variable = context.get(name);
        Variable newVariable = magicStack.pop();
        // Type checking
        if (!newVariable.getType().equals(variable.getType())) {
            // throw an exception
            throw new TypeError(String.format("%s is not of type %s", name, newVariable.getType())); // We could add some text here (to say which variable failed).
        }
        variable = factory.createVariable(newVariable.getValue(), newVariable.getType());

        context.put(name, variable);
    }

    @Override
    public void visitDeclareAssignNode(DeclareAssignNode node) {
        node.getExpression().visit(this);
        String name = node.getIdentifier().getName();
        // We need to check if it already exists.
        if (context.containsKey(name)) {
            throw new ReferenceError(String.format("%s is already defined", name));
        }
        // We need to check if we are referencing a variable that exists.
        Variable newVariable = magicStack.pop();
        Variable variable;
        // Can this type checking be done in a better way?
        if (!newVariable.getType().equals(node.getType())) {
            // throw an exception
            throw new TypeError(String.format("%s is not of type %s", name, newVariable.getType())); // We could add some text here (to say which variable failed).
        }
        variable = factory.createVariable(newVariable.getValue(), newVariable.getType());

        context.put(name, variable);
    }

    @Override
    public void visitDeclareNode(DeclareNode node) {
        // We are creating a variable without a value, how should this work?
        String name = node.getIdentifier().getName();
        // Sometimes there is no value in the stack.
        if (context.containsKey(name)) {
            throw new ReferenceError(String.format("%s is already defined", name));
        }
        Variable variable;
        // We need to check if it already exists.
        variable = factory.createVariable(null, node.getType());

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

        magicStack.push(value1.divide(value2));
    }

    @Override
    public void visitExpressionSingleNode(ExpressionSingleNode node) {
        node.getTerm().visit(this);
    }

    @Override
    public void visitIdentifierNode(IdentifierNode node) {
        String name = node.getName();

        if (!context.containsKey(name)) {
            throw new ReferenceError(String.format("%s is not defined", name));
        }
        Variable variable = context.get(name);
        // This might be null, exception.
        magicStack.push(variable);
    }

    @Override
    public void visitMultiplicationNode(MultiplicationNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        Variable value1 = magicStack.pop();
        Variable value2 = magicStack.pop();

        magicStack.push(value1.multiply(value2));
    }

    @Override
    public void visitNumberNode(NumberNode node) {
        Variable variable = new NumberVariable(node.getValue());
        magicStack.push(variable);
    }

    // It will probably be a good idea to create an abstraction for the console.
    // It could be an interface that can have different standard outs, although the default will be console.
    @Override
    public void visitPrintNode(PrintNode node) {
        // We need the expression to be saved somewhere.
        node.getExpression().visit(this);
        // We print after we are sure the value is in the "stack"
        String value = magicStack.pop().getValue(); // When used, we should remove it.
        output.output(value);
    }

    @Override
    public void visitStringNode(StringNode node) {
        Variable variable = new StringVariable(node.getValue());
        magicStack.push(variable);
    }

    @Override
    public void visitSubtractionNode(SubtractionNode node) {
        node.getRight().visit(this);
        node.getLeft().visit(this);
        Variable value1 = magicStack.pop();
        Variable value2 = magicStack.pop();

        magicStack.push(value1.subtract(value2));
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
