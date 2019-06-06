package interpreter;

import parser.nodes.ASTNode;

public interface Interpreter {

    void interpret(ASTNode tree);
}
