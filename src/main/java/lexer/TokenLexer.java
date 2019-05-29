package lexer;

public class TokenLexer implements Lexer {

    private LexerState state;

    private LexerState initialState;

    public TokenLexer() {
        // Should the logic for creating all the states be handled here?
        this.initialState = new SpaceState();
        this.state = initialState;
    }

    // Where should the token output be taken from? They all need to have the same instance.

    // This should probably all change, it is a first attempt to get a general idea of how it would work.
    public TokenOutput lex(InputStream stream) {
        TokenOutput output = new TypeScriptTokenOutput();
        // These other states could be created in the initial state, easily.

        while(stream.hasNext()) {
            // Equal state to the next state, returned by process character
            StateResponse response = state.processCharacter(stream.peek());
            if (response.hasToken()) {
                output.writeToken(response.getToken());
            }
            if (response.consumedChar()) {
                stream.consume(); // We consume the character.
            }
            state = response.getNextState();
        }
        state.reset(); // Reset the state if it had any info in the buffer.
        state = initialState; // Reset the state for next input.
        return output;
    }
}
