package lexer;

import java.util.ArrayList;
import java.util.List;

public class IdentifierState implements DetectorState {

    private TokenOutput output;

    private InputStream input;

    private String buffer; // Check if this way of doing this is very inefficient.

    // private Map<CharacterChecker, DetectorState> transitions; // Is this a good way of doing this?
    // The above code is unnecessary to be honest, we need only two transitions.

    private CharacterChecker allowedChecker; // Are this going to be settable in the constructor?

    private CharacterChecker delimiterChecker;

    private KeywordChecker keywordChecker;

    private DetectorState state;

    public IdentifierState(TokenOutput output, DetectorState state, InputStream input) {
        this.output = output;
        // How do we know if we are the first character?
        // What a good question
        this.allowedChecker = new OneCharacterRegex("[0-9a-zA-Z]");
        this.delimiterChecker = new OneCharacterRegex(";| |:|(|)|\n"); // Should this regex be provided by someone else?
        this.state = state;
        this.input = input;
        List<String> list = new ArrayList<>();
        // This should be defined somewhere else, of course.
        list.add("let");
        list.add("string");
        list.add("number");
        list.add("print");
        this.keywordChecker = new KeywordChecker(list);
        this.buffer = ""; // If this really necessary?
    }

    // States could return the next state after what they have processed.
    public DetectorState processCharacter() {

        char character = this.input.peek();

        // If it is the first character, we have a different check
        // What is a good way to express this?

        // There is no need to check if the character is valid, as it must be.
        // It will always be a letter, which is also one of the characters that is always accepted.
        // No additional checks need to be added.


        // Next character is part of identifier
        if (allowedChecker.check(character)) {
            this.buffer += character;
            this.input.consume();
            return this;
        }

        // The token we used does not need to be processed, we should someone tell that to the caller
        // This is important for this to work correctly.
        if (delimiterChecker.check(character)) {

            if (keywordChecker.check(this.buffer)) {
                // Create a keyword token
                this.output.writeToken(new TypeScriptToken(this.buffer, "keyword"));
            } else {
                this.output.writeToken(new TypeScriptToken(this.buffer, "identifier"));
            }
            // How should it be done?
            this.buffer = ""; // We need to clear the buffer.
            return this.state;
        }

        // Otherwise there was an error, the input was invalid, so we throw an exception.
        throw new InvalidInputException();
    }
}
