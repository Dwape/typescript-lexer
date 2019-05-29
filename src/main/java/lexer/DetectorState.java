package lexer;

// This may require a refactor in the future, if I come up with a better name.
public interface DetectorState {

    // Processes a character and acts accordingly.
    DetectorState processCharacter();
}
