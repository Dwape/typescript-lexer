package parser;

import lexer.Token;
import lexer.TokenStream;
import lexer.TokenType;

public class DeclarationState implements ParserState {

    private ExpressionState expression;

    public DeclarationState() {
        this.expression = new ExpressionState();
    }

    @Override
    public StatementNode parse(TokenStream stream) {
        Token token = stream.peek();

        if (!(token.getType() == TokenType.LET)) {
            throw new SyntaxErrorException();
        }
        stream.consume();

        token = stream.peek();
        // Try to look for a more elegant way of solving this.
        if (!(token.getType() == TokenType.IDENTIFIER)) {
            throw new SyntaxErrorException();
        }
        stream.consume(); // Consume the identifier token
        // We need to save the identifier node
        IdentifierNode node = new IdentifierNode(token.getContent()); // This could be improved.
        token = stream.peek();
        if (!(token.getType() == TokenType.COLON)) {
            throw new SyntaxErrorException();
        }
        stream.consume();

        token = stream.peek();
        // We have the type here, so something could be done about it
        if (!(token.getType() == TokenType.NUMBER_TYPE) && !(token.getType() == TokenType.STRING_TYPE)) { // Should it consume the semi-colon?
            throw new SyntaxErrorException();
        }
        String type;
        if (token.getType() == TokenType.NUMBER_TYPE) {
            type = "number";
        } else {
            type = "string";
        }
        stream.consume();

        // Here two things can happen
        // We can have a declaration or if might end.
        token = stream.peek();
        if (token.getType() == TokenType.SEMI_COLON) { // Should it consume the semi-colon?
            // We are done, return;
            return new DeclareNode(node, type);
        }
        if (!(token.getType() == TokenType.EQUALS)) { // Should it consume the semi-colon?
            // We are done, return;
            throw new SyntaxErrorException();
        }
        stream.consume();
        ExpressionNode node2 = expression.parse(stream);
        token = stream.peek();
        if (!(token.getType() == TokenType.SEMI_COLON)) { // Should it consume the semi-colon?
            // We are done, return;
            throw new SyntaxErrorException();
        }
        stream.consume();
        return new DeclareAssignNode(node, type, node2);
    }
}
