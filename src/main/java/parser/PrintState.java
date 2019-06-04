package parser;

import lexer.Token;
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
        Token token = stream.peek();
        // Try to look for a more elegant way of solving this.
        if (!(token.getType() == TokenType.PRINT)) {
            throw new SyntaxErrorException();
        }
        stream.consume(); // Consume the print token
        token = stream.peek();
        if (!(token.getType() == TokenType.LEFT_PARENTHESIS)) {
            throw new SyntaxErrorException();
        }
        stream.consume();
        // We need to check if the following token is a (
        // Parse the expression
        ExpressionNode node = expression.parse(stream);
        token = stream.peek();
        if (!(token.getType() == TokenType.RIGHT_PARENTHESIS)) {
            throw new SyntaxErrorException();
        }
        stream.consume();
        token = stream.peek();
        if (!(token.getType() == TokenType.SEMI_COLON)) { // Should it consume the semi-colon?
            throw new SyntaxErrorException();
        }
        stream.consume();
        return new PrintNode(node);
    }
}
