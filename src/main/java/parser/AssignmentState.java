package parser;

import lexer.Token;
import lexer.TokenStream;
import lexer.TokenType;

public class AssignmentState implements ParserState{

    private ExpressionState expression;

    public AssignmentState() {
        this.expression = new ExpressionState();
    }

    @Override
    public StatementNode parse(TokenStream stream) {

        Token token = stream.peek();
        // Try to look for a more elegant way of solving this.
        if (!(token.getType() == TokenType.IDENTIFIER)) {
            throw new SyntaxErrorException();
        }
        stream.consume(); // Consume the identifier token
        // We need to save the identifier node
        IdentifierNode node = new IdentifierNode(token.getContent()); // This could be improved.
        token = stream.peek();
        if (!(token.getType() == TokenType.EQUALS)) {
            throw new SyntaxErrorException();
        }
        stream.consume();
        // We need to check if the following token is a (
        // Parse the expression
        ExpressionNode node2 = expression.parse(stream);
        token = stream.peek();
        if (!(token.getType() == TokenType.SEMI_COLON)) { // Should it consume the semi-colon?
            throw new SyntaxErrorException();
        }
        stream.consume();
        return new AssignmentNode(node, node2);
    }
}
