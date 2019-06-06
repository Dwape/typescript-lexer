package lexer;

import java.util.HashMap;
import java.util.Map;

// This State could have a better name, showing it is the main one, and the most important one.
public class SpaceState implements LexerState {

    private CharacterChecker ignoreChecker;

    // How should the different actions be defined?
    private Map<CharacterChecker, LexerState> transitions;

    public SpaceState() {
        this.transitions = new HashMap<>();
        this.ignoreChecker = new OneCharacterRegex(" |\n|\\$"); // Check if the regex is correct
        // Create all the states.
        LexerState identifierState = new IdentifierState(this);
        LexerState delimiterState = new DelimiterState(this);
        LexerState stringState = new StringState(this);
        LexerState numberState = new NumberState(this);
        // Add all the transitions to the other states.
        addTransition(identifierState, new OneCharacterRegex("[a-zA-Z_]")); // it must start with a letter.
        addTransition(delimiterState, new OneCharacterRegex(";|:|\\(|\\)|=|\\+|-|\\*|\\/"));
        addTransition(stringState, new OneCharacterRegex("\"|'"));
        addTransition(numberState, new OneCharacterRegex("[0-9]|\\.")); // We accept .
    }

    public void addTransition(LexerState state, CharacterChecker checker) {
        this.transitions.put(checker, state);
    }

    @Override
    public StateResponse processCharacter(char character) {

        // Remove this switch (or at least make it nicer.

        if (this.ignoreChecker.check(character)) {
            return new TypeScriptStateResponse(this, true);
        }

        // Check if there is any transition for the character
        // This is not a good way of doing it, find a better one.

        // We could find a nicer way to do this.
        for (CharacterChecker key : this.transitions.keySet()) {
            if (key.check(character)) return new TypeScriptStateResponse(this.transitions.get(key), false);
        }

        // If there isn't, throw an exception
        throw new InvalidInputException();
    }

    @Override
    public void reset() {
        // We don't need to do anything here, as we save no values.
        // Could reset all other states, but I think it is not necessary.
    }
}
