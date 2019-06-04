package parser;

import lexer.TokenStream;
import lexer.TokenType;
import parser.nodes.ExpressionNode;

public class PrintState implements ParserState {

    private ExpressionState expression;

    // We don't really need a buffer here as it is quite simple
    // Although maybe adding one could help with clarity

    public PrintState() {
        this.expression = new ExpressionState();
    }

    @Override
    public PrintNode parse(TokenStream stream) {
        // The first thing received will always be a print token
        // Otherwise we wouldn't be called, but we can check it anyways.
        checkTokenType(stream, TokenType.PRINT);
        checkTokenType(stream, TokenType.LEFT_PARENTHESIS);
        // We need to check if the following token is a (
        // Parse the expression
        ExpressionNode node = expression.parse(stream);

        checkTokenType(stream, TokenType.RIGHT_PARENTHESIS);
        checkTokenType(stream, TokenType.SEMI_COLON);
        return new PrintNode(node);
    }

    // Throws an exception if there is an invalid token
    private void checkTokenType(TokenStream stream, TokenType type) {
        if (stream.peek().getType() != type) {
            throw new SyntaxErrorException();
        }
        stream.consume();
    }
}
