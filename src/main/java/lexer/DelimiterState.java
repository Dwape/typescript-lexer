package lexer;

import java.util.HashMap;
import java.util.Map;

public class DelimiterState implements LexerState {

    private LexerState state; // The space state to return to.

    // This should define a token type for the character (it is currently a string, but it will most likely change).
    private Map<Character, String> mapping;

    public DelimiterState(LexerState state) {
        this.state = state;

        this.mapping = new HashMap<>();
        // Initialization
        // These could probably be defined somewhere else, I guess.
        this.mapping.put('+', "plus");
        this.mapping.put('-', "minus");
        this.mapping.put('/', "divided");
        this.mapping.put('(', "left_parenthesis");
        this.mapping.put(')', "right_parenthesis");
        this.mapping.put('=', "equals");
        this.mapping.put(';', "colon");
        this.mapping.put(':', "semi-colon");
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
