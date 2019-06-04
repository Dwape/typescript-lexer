package parser;

import lexer.Token;
import lexer.TokenStream;
import lexer.TokenType;

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
        Token token = stream.peek();
        if (token.getType() == TokenType.PRINT) {
            StatementNode node = print.parse(stream);
            buffer.addStatement(node);
            return parse(stream);
        }
        if (token.getType() == TokenType.LET) {
            StatementNode node = declaration.parse(stream);
            buffer.addStatement(node);
            return parse(stream);
        }
        if (token.getType() == TokenType.IDENTIFIER) {
            StatementNode node = assignment.parse(stream);
            buffer.addStatement(node);
            return parse(stream);
        }
        // All statement options have been considered above
        throw new SyntaxErrorException();
    }

    private void reset() {
        buffer = null;
    }
}
