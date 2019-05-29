package lexer;

// This may require a refactor in the future, if I come up with a better name.
public interface LexerState {

    // Processes a character and acts accordingly.
    StateResponse processCharacter(char character);

    void reset();
}
