package lexer;

import lexer.token.Token;

// This could have a nicer behavior, but for now this works.
// We should create an interface for this, it will be better.
public class TypeScriptStateResponse implements StateResponse{

    private LexerState nextState;

    private boolean hasToken;

    private Token token;

    private boolean consumedChar;

    // Used if no token is found
    public TypeScriptStateResponse(LexerState state, boolean consumed) {
        this.nextState = state;
        this.consumedChar = consumed;
        this.hasToken = false;
    }

    // Used if a token was found
    public TypeScriptStateResponse(LexerState state, boolean consumed, Token token) {
        this.nextState = state;
        this.token = token;
        this.hasToken = token != null;
        this.consumedChar = consumed;
    }

    public LexerState getNextState() {
        return this.nextState;
    }

    public boolean hasToken() {
        return this.hasToken;
    }

    // If there is not token, what should this method do?
    public Token getToken() {
        return this.token;
    }

    public boolean consumedChar() {
        return this.consumedChar;
    }
}
