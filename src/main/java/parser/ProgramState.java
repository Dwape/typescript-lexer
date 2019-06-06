package parser;

import lexer.token.Token;
import lexer.token.TokenPosition;
import lexer.token.TokenStream;
import lexer.token.TokenType;
import parser.nodes.ProgramNode;
import parser.nodes.StatementNode;
import parser.nodes.TreeNode;

public class ProgramState implements ParserState {

    private ProgramNode buffer;

    private PrintState print;

    private AssignmentState assignment;

    private DeclarationState declaration;

    public ProgramState() {
        this.buffer = new TreeNode(); // Initialize it empty.
        this.print = new PrintState();
        this.assignment = new AssignmentState();
        this.declaration = new DeclarationState();
    }

    // returns the whole tree of the program.
    @Override
    public ProgramNode parse(TokenStream stream) {

        if (!stream.hasNext()) {
            // We've finished parsing, hooray!
            ProgramNode result = buffer;
            reset();
            return result;
        }
        // We still have to continue, but we are close now.
        StatementNode node;
        Token token = stream.peek();

        if (token.getType() == TokenType.PRINT) node = print.parse(stream);
        else if (token.getType() == TokenType.LET) node = declaration.parse(stream);
        else if (token.getType() == TokenType.IDENTIFIER) node = assignment.parse(stream);
        else {
            TokenPosition position = token.getPosition();
            throw new SyntaxErrorException(String.format("Syntax error at (%d,%d), unexpected token %s", position.getCharStart(), position.getLine(), token.getContent()));
        }

        buffer.addStatement(node);
        return parse(stream);
    }

    private void reset() {
        buffer = null;
    }
}
