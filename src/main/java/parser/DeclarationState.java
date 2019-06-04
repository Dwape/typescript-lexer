package parser;

import lexer.Token;
import lexer.TokenStream;
import lexer.TokenType;
import parser.nodes.*;

public class DeclarationState implements ParserState {

    private ExpressionState expression;

    public DeclarationState() {
        this.expression = new ExpressionState();
    }

    @Override
    public StatementNode parse(TokenStream stream) {

        checkTokenType(stream, TokenType.LET);

        Token token = checkTokenType(stream, TokenType.IDENTIFIER);

        IdentifierNode identifier = new IdentifierNode(token.getContent()); // This could be improved.

        checkTokenType(stream, TokenType.COLON);

        token = checkTwoTypes(stream, TokenType.NUMBER_TYPE, TokenType.STRING_TYPE);

        String type = getType(token);

        // Here two things can happen
        // We can have a declaration or if might end.
        if (endOfElement(stream)) { // Should it consume the semi-colon?
            // We are done, return;
            return new DeclareNode(identifier, type);
        }

        checkTokenType(stream, TokenType.EQUALS);

        ExpressionNode node = expression.parse(stream);

        checkTokenType(stream, TokenType.SEMI_COLON);

        return new DeclareAssignNode(identifier, type, node);
    }

    // Throws an exception if there is an invalid token
    private Token checkTokenType(TokenStream stream, TokenType type) {
        Token token = stream.peek();
        if (token.getType() != type) {
            throw new SyntaxErrorException();
        }
        stream.consume();
        return token;
    }

    // This method will only be used once and may not really be necessary.
    private Token checkTwoTypes(TokenStream stream, TokenType type1, TokenType type2) {
        Token token = stream.peek();
        if (token.getType() != type1 && token.getType() != type2) {
            throw new SyntaxErrorException();
        }
        stream.consume();
        return token;
    }

    private String getType(Token token) {
        if (token.getType() == TokenType.NUMBER_TYPE)
            return "number";
        if (token.getType() == TokenType.STRING_TYPE)
            return "string";
        throw new SyntaxErrorException(); // This shouldn't happen, but just in case.
    }

    private boolean endOfElement(TokenStream stream) {
        Token token = stream.peek();
        stream.consume();
        return token.getType() == TokenType.SEMI_COLON;
    }
}
