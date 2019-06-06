package lexer;

import lexer.token.Token;
import lexer.token.TokenType;
import lexer.token.TypeScriptToken;

import java.util.HashMap;
import java.util.Map;

// A state for the delimiter characters.
// All delimiters will be single characters.
public class DelimiterState implements LexerState {

    private LexerState state; // The space state to return to.

    // This should define a token type for the character (it is currently a string, but it will most likely change).
    private Map<Character, TokenType> mapping;

    public DelimiterState(LexerState state) {
        this.state = state;

        this.mapping = new HashMap<>();
        // Initialization
        // These could probably be defined somewhere else, I guess.
        this.mapping.put('+', TokenType.PLUS);
        this.mapping.put('-', TokenType.MINUS);
        this.mapping.put('*', TokenType.MULTIPLICATION);
        this.mapping.put('/', TokenType.DIVISION);
        this.mapping.put('(', TokenType.LEFT_PARENTHESIS);
        this.mapping.put(')', TokenType.RIGHT_PARENTHESIS);
        this.mapping.put('=', TokenType.EQUALS);
        this.mapping.put(';', TokenType.SEMI_COLON);
        this.mapping.put(':', TokenType.COLON);
        // I think no symbol is missing.
    }

    @Override
    public StateResponse processCharacter(char character) {

        // The character received will always be converted to a token and consumed.

        Token newToken = new TypeScriptToken(String.valueOf(character), this.mapping.get(character));
        // reset(); // we should reset here if there was anything to reset.
        return new TypeScriptStateResponse(this.state, true, newToken);
    }

    @Override
    public void reset() {
        // No need to reset anything.
    }
}
