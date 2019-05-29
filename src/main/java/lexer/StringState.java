package lexer;

public class StringState implements LexerState{

    private LexerState state;

    private String buffer;

    private CharacterChecker endChecker; // Checks if the string ends.

    public StringState(LexerState state) {
        this.state = state;
        this.buffer = ""; // The buffer starts empty.
    }

    @Override
    public StateResponse processCharacter(char character) {
        // We need to check if it is the first character, to know if we are receiving the " or '
        // How are we going to detect the " or '?
        if (endChecker == null) { // This may not be the nicest way of doing it
            // Using the first character has an advantage
            // If we change which characters start a string, this should automatically work.
            endChecker = new OneCharacterRegex(String.valueOf(character)); // This may not work.
            buffer += character; // Do we want to keep the "? Easy to change.
            return new TypeScriptStateResponse(this, true);
        }

        if (endChecker.check(character)) {
            buffer += character;
            Token newToken = new TypeScriptToken(buffer, "string"); // Or literal, maybe.
            reset(); // We should reset for next time we are called.
            return new TypeScriptStateResponse(state, true, newToken);
        }

        // Any other character will ba allowed in the string.
        buffer += character;
        return new TypeScriptStateResponse(this, true);
    }

    @Override
    public void reset() {
        buffer = ""; // Reset the buffer.
        endChecker = null; // We need to delete the end checker as the next time it might be different.
    }
}