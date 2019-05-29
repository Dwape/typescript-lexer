package lexer;

import java.util.HashMap;
import java.util.Map;

public class DelimiterState implements DetectorState{

    private TokenOutput output;

    private InputStream input;

    private DetectorState state;

    // This should define a token type for the character (it is currently a string, but it will most likely change).
    private Map<Character, String> mapping;

    public DelimiterState(TokenOutput output, DetectorState state, InputStream input) {
        this.output = output;
        this.state = state;
        this.input = input;

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
    public DetectorState processCharacter() {

        char character = this.input.peek();
        // The character received will always be converted to a token and consumed.

        this.output.writeToken(new TypeScriptToken(String.valueOf(character), this.mapping.get(character)));
        this.input.consume();

        return this.state;
    }
}
