package parser;

import lexer.token.Token;
import lexer.token.TokenPosition;
import lexer.token.TokenStream;
import lexer.token.TokenType;
import parser.nodes.AssignmentNode;
import parser.nodes.ExpressionNode;
import parser.nodes.IdentifierNode;
import parser.nodes.StatementNode;

public class AssignmentState implements ParserState{

    private ExpressionState expression;

    public AssignmentState() {
        this.expression = new ExpressionState();
    }

    @Override
    public StatementNode parse(TokenStream stream) {

        // We need to save the identifier node
        Token token = checkTokenType(stream, TokenType.IDENTIFIER);
        IdentifierNode type = new IdentifierNode(token.getContent()); // This could be improved.

        checkTokenType(stream, TokenType.EQUALS);
        // We need to check if the following token is a (
        // Parse the expression
        ExpressionNode node = expression.parse(stream);

        checkTokenType(stream, TokenType.SEMI_COLON);

        return new AssignmentNode(type, node);
    }

    // Throws an exception if there is an invalid token
    private Token checkTokenType(TokenStream stream, TokenType type) {
        Token token = stream.peek();
        if (token.getType() != type) {
            TokenPosition position = token.getPosition();
            throw new SyntaxErrorException(String.format("Syntax error at (%d,%d), unexpected token %s", position.getCharStart(), position.getLine(), token.getContent()));
        }
        stream.consume();
        return token;
    }
}
