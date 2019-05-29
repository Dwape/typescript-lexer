package lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// A state for the delimiter characters.
// All delimiters will be single characters.
public class SpaceState implements DetectorState {

    private InputStream input;

    private TokenOutput output;

    // How should the different actions be defined?
    private Map<CharacterChecker, DetectorState> transitions;

    public SpaceState(InputStream input, TokenOutput output) {
        this.input = input;
        this.output = output;
        this.transitions = new HashMap<>();
    }

    public void addTransition(DetectorState state, CharacterChecker checker) {
        this.transitions.put(checker, state);
    }

    @Override
    public DetectorState processCharacter() {
        char character = this.input.peek();

        // Remove this switch (or at least make it nicer.

        if (character == ' ' || character == '\n') {
            this.input.consume();
            return this;
        }

        // Check if there is any transition for the character
        // This is not a good way of doing it, find a better one.

        for (CharacterChecker key : this.transitions.keySet()) {
            if (key.check(character)) return this.transitions.get(key); // This is ugly as well
        }

        // If there isn't, throw an exception
        throw new InvalidInputException();
    }
}
