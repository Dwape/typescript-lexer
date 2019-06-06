package parser;

import lexer.token.Token;
import lexer.token.TokenPosition;
import lexer.token.TokenStream;
import lexer.token.TokenType;
import parser.nodes.ExpressionNode;
import parser.nodes.PrintNode;

public class PrintState implements ParserState {

    private ExpressionState expression;
    private TokenTypeChecker checker;

    // We don't really need a buffer here as it is quite simple
    // Although maybe adding one could help with clarity

    public PrintState() {
        this.expression = new ExpressionState();
        this.checker = new TokenTypeChecker();
    }

    @Override
    public PrintNode parse(TokenStream stream) {
        // The first thing received will always be a print token
        // Otherwise we wouldn't be called, but we can check it anyways.
        checker.checkTokenType(stream, TokenType.PRINT);
        checker.checkTokenType(stream, TokenType.LEFT_PARENTHESIS);
        // We need to check if the following token is a (
        // Parse the expression
        ExpressionNode node = expression.parse(stream);

        checker.checkTokenType(stream, TokenType.RIGHT_PARENTHESIS);
        checker.checkTokenType(stream, TokenType.SEMI_COLON);
        return new PrintNode(node);
    }
}
