package lexer;

import lexer.token.*;

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

        // Add an end of file symbol that is consumed but there is no token created.
        stream.append('$'); // EOF chosen symbol.

        while(stream.hasNext()) {
            // Equal state to the next state, returned by process character
            char character = stream.peek();

            StateResponse response;

            try {
                response = state.processCharacter(stream.peek());
            } catch (InvalidInputException exception) {
                // What should we return here?
                // We could have an invalid token to be honest.
                Token invalidToken = new TypeScriptToken("", TokenType.INVALID);
                locator.addLocation(invalidToken);
                int tokenChar = invalidToken.getPosition().getCharStart();
                int tokenLine = invalidToken.getPosition().getLine();
                throw new InvalidInputException("Invalid token at (" + tokenChar + "," + tokenLine + ")");
            }

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
