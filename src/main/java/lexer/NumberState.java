package lexer;

import lexer.token.Token;
import lexer.token.TokenType;
import lexer.token.TypeScriptToken;

public class NumberState implements LexerState{

    private LexerState state;

    private String buffer;

    private CharacterChecker allowedChecker;

    private CharacterChecker delimiterChecker;

    private CharacterChecker decimalChecker; // Checks if we start a decimal

    private boolean isDecimal;

    public NumberState(LexerState state) {
        this.state = state;
        this.buffer = ""; // Buffer starts empty.
        // It might be better for this regex to be provided by someone else, so that it can change.
        this.delimiterChecker = new OneCharacterRegex(" |\n|;|:|\\(|\\)|=|\\+|-|\\*|\\/|\\$");
        this.allowedChecker = new OneCharacterRegex("[0-9]");
        this.decimalChecker = new OneCharacterRegex("\\.");
        this.isDecimal = false;
    }

    @Override
    public StateResponse processCharacter(char character) {
        // Can the first char of the number be .?

        // The ifs might be better, check if it can be optimized.

        // We need to check if we receive a .
        if (decimalChecker.check(character) && !isDecimal) {
            // It is a .
            // We only accept it if we were not in the decimal part already.
            buffer += character;
            isDecimal = true;
            return new TypeScriptStateResponse(this, true);
        }

        if (allowedChecker.check(character)) {
            buffer += character;
            return new TypeScriptStateResponse(this, true);
        }

        if (delimiterChecker.check(character)) {
            // We don't add the character to the buffer.
            // We need to check if the last element is a .
            if (delimiterChecker.check(buffer.charAt(buffer.length()-1))) {
                // Throw an exception.
                throw new InvalidInputException();
            }
            // The number is valid
            Token newToken = new TypeScriptToken(buffer, TokenType.NUMBER);
            reset();
            return new TypeScriptStateResponse(state, false, newToken);
        }

        throw new InvalidInputException();
    }

    @Override
    public void reset() {
        this.buffer = "";
    }
}
