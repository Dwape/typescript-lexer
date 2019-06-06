package lexer;

import lexer.token.Token;
import lexer.token.TokenStream;
import lexer.token.TypeScriptTokenStream;

import java.util.ArrayList;
import java.util.List;

public class TokenLexer implements Lexer {

    private LexerState state;

    private LexerState initialState;

    private TokenLocator locator;

    public TokenLexer() {
        // Should the logic for creating all the states be handled here?
        this.initialState = new SpaceState();
        this.state = initialState;
        this.locator = new TokenLocator();
    }

    // Where should the token output be taken from? They all need to have the same instance.

    // This should probably all change, it is a first attempt to get a general idea of how it would work.
    public TokenStream lex(InputStream stream) {
        List<Token> tokens = new ArrayList<>();

        // All this behavior should most likely be done somewhere else.
        // Try to move it somewhere else

        // These other states could be created in the initial state, easily.

        while(stream.hasNext()) {
            // Equal state to the next state, returned by process character
            char character = stream.peek();

            StateResponse response = state.processCharacter(stream.peek());

            if (response.consumedChar()) {
                locator.nextChar(character); // Only if we consumed the character.
                stream.consume(); // We consume the character.
            }

            if (response.hasToken()) {
                Token token = response.getToken();
                locator.addLocation(token); // Is this okay?
                tokens.add(token); // Add the token,
            }

            state = response.getNextState();
        }
        state.reset(); // Reset the state if it had any info in the buffer.
        state = initialState; // Reset the state for next input.
        return new TypeScriptTokenStream(tokens);
    }
}
