package lexer;

public interface StateResponse {

    public LexerState getNextState();

    public boolean hasToken();

    public Token getToken();

    public boolean consumedChar();
}
