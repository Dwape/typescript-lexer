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
    private TokenTypeChecker checker;

    public AssignmentState() {
        this.expression = new ExpressionState();
        this.checker = new TokenTypeChecker();
    }

    @Override
    public StatementNode parse(TokenStream stream) {

        // We need to save the identifier node
        Token token = checker.checkTokenType(stream, TokenType.IDENTIFIER);
        IdentifierNode type = new IdentifierNode(token.getContent()); // This could be improved.

        checker.checkTokenType(stream, TokenType.EQUALS);
        // We need to check if the following token is a (
        // Parse the expression
        ExpressionNode node = expression.parse(stream);

        checker.checkTokenType(stream, TokenType.SEMI_COLON);

        return new AssignmentNode(type, node);
    }
}
