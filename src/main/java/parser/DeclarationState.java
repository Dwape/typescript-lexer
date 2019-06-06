package parser;

import lexer.token.Token;
import lexer.token.TokenStream;
import lexer.token.TokenType;
import parser.nodes.*;

public class DeclarationState implements ParserState {

    private ExpressionState expression;
    private TokenTypeChecker checker;

    public DeclarationState() {
        this.expression = new ExpressionState();
        this.checker = new TokenTypeChecker();
    }

    @Override
    public StatementNode parse(TokenStream stream) {

        checker.checkTokenType(stream, TokenType.LET);

        Token token = checker.checkTokenType(stream, TokenType.IDENTIFIER);

        IdentifierNode identifier = new IdentifierNode(token.getContent()); // This could be improved.

        checker.checkTokenType(stream, TokenType.COLON);

        token = checker.checkTwoTypes(stream, TokenType.NUMBER_TYPE, TokenType.STRING_TYPE);

        String type = getType(token);

        // Here two things can happen
        // We can have a declaration or if might end.
        if (endOfElement(stream)) { // Should it consume the semi-colon?
            // We are done, return;
            stream.consume();
            return new DeclareNode(identifier, type);
        }

        checker.checkTokenType(stream, TokenType.EQUALS);

        ExpressionNode node = expression.parse(stream);

        checker.checkTokenType(stream, TokenType.SEMI_COLON);

        return new DeclareAssignNode(identifier, type, node);
    }

    private String getType(Token token) {
        if (token.getType() == TokenType.NUMBER_TYPE)
            return "number";
        else return "string";
    }

    private boolean endOfElement(TokenStream stream) {
        if (!stream.hasNext()) { // Is this actually necessary?
            throw new SyntaxErrorException("Unexpected end of input");
        }
        Token token = stream.peek();
        return token.getType() == TokenType.SEMI_COLON;
    }
}
