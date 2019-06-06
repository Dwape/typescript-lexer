package parser;

import lexer.token.Token;
import lexer.token.TokenStream;
import lexer.token.TokenType;

public class TokenTypeChecker {

    private SyntaxErrorLogger logger;

    public TokenTypeChecker() {
        this.logger = new SyntaxErrorLogger();
    }

    public Token checkTokenType(TokenStream stream, TokenType type) {
        if (!stream.hasNext()) {
            throw new SyntaxErrorException("Unexpected end of input");
        }
        Token token = stream.peek();
        if (token.getType() != type) {
            logger.tokenError(token);
        }
        stream.consume();
        return token;
    }

    public Token checkTwoTypes(TokenStream stream, TokenType type1, TokenType type2) {
        if (!stream.hasNext()) {
            throw new SyntaxErrorException("Unexpected end of input");
        }
        Token token = stream.peek();
        if (token.getType() != type1 && token.getType() != type2) {
            logger.tokenError(token);
        }
        stream.consume();
        return token;
    }
}
