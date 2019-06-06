package parser;

import lexer.token.Token;
import lexer.token.TokenPosition;

public class SyntaxErrorLogger {

    public void tokenError(Token token) throws SyntaxErrorException{
        TokenPosition position = token.getPosition();
        int line = position.getLine();
        int character = position.getCharStart();
        String content = token.getContent();
        throw new SyntaxErrorException(
                String.format("Syntax error at (%d,%d), unexpected token %s", character, line, content));
    }
}
