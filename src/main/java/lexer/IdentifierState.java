package lexer;

import lexer.token.Token;
import lexer.token.TokenType;
import lexer.token.TypeScriptToken;

public class IdentifierState implements LexerState {

    private String buffer; // Check if this way of doing this is very inefficient.

    // private Map<CharacterChecker, LexerState> transitions; // Is this a good way of doing this?
    // The above code is unnecessary to be honest, we need only two transitions.

    private CharacterChecker allowedChecker; // Are this going to be settable in the constructor?

    private CharacterChecker delimiterChecker;

    private KeywordChecker keywordChecker;

    private LexerState state;

    public IdentifierState(LexerState state) {
        // How do we know if we are the first character?
        // What a good question
        this.allowedChecker = new OneCharacterRegex("[0-9a-zA-Z_]");
        this.delimiterChecker = new OneCharacterRegex(" |\n|;|:|\\(|\\)|=|\\+|-|\\*|\\/|\\$"); // Should this regex be provided by someone else?
        this.state = state;
        this.keywordChecker = new KeywordChecker();
        this.buffer = ""; // If this really necessary?
    }

    // States could return the next state after what they have processed.
    @Override
    public StateResponse processCharacter(char character) {

        // If it is the first character, we have a different check
        // What is a good way to express this?

        // There is no need to check if the character is valid, as it must be.
        // It will always be a letter, which is also one of the characters that is always accepted.
        // No additional checks need to be added.


        // Next character is part of identifier
        if (allowedChecker.check(character)) {
            this.buffer += character; // Is this a good way of doing this?
            return new TypeScriptStateResponse(this, true);
        }

        // The token we used does not need to be processed, we should someone tell that to the caller
        // This is important for this to work correctly.
        if (delimiterChecker.check(character)) {

            Token newToken;
            // Check if the token is a key word.
            TokenType type = keywordChecker.check(this.buffer);
            if (type != null) {
                // Create a keyword token
                // Keyword could be more specific to make the parser easier.
                newToken = new TypeScriptToken(this.buffer, type);
            } else {
                newToken = new TypeScriptToken(this.buffer, TokenType.IDENTIFIER);
            }
            // How should it be done?
            reset(); // We need to reset the state.
            return new TypeScriptStateResponse(this.state, false, newToken);
        }

        // Otherwise there was an error, the input was invalid, so we throw an exception.
        throw new InvalidInputException();
    }

    @Override
    public void reset() {
        this.buffer = ""; // Reset the buffer.
    }
}
