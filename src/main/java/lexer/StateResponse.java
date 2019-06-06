package lexer;

import lexer.token.Token;

public interface StateResponse {

    public LexerState getNextState();

    public boolean hasToken();

    public Token getToken();

    public boolean consumedChar();
}
