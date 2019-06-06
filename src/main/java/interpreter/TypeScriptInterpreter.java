package interpreter;

import parser.NodeVisitor;
import parser.nodes.ASTNode;

public class TypeScriptInterpreter implements Interpreter {

    private InterpreterVisitor visitor;

    public TypeScriptInterpreter() {
        this.visitor = new InterpreterVisitor();
    }

    @Override
    public void interpret(ASTNode tree) {
        visitor.start(tree);
    }
}
