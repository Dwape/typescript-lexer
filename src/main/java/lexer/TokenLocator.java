package lexer;

import lexer.token.Token;
import lexer.token.TypeScriptTokenPosition;

public class TokenLocator {

    private int currentLine; // line starts at 1.
    private int currentChar;
    private int lineStart;
    private int charStart; // characters also start at 1
    // Do we care about where the token ends?

    public TokenLocator() {
        this.currentLine = 1;
        this.currentChar = 1;
        this.lineStart = currentLine;
        this.charStart = currentChar;
    }

    // Adds the location to the token.
    public void addLocation(Token token) {
        // The length is not as easy to calculate now, is it?
        // This will not work correctly for multi-line tokens.
        token.addLocation(new TypeScriptTokenPosition(lineStart, charStart)); // currentChar-charStart
        newToken();
    }

    public void nextChar(char character) {
        if (character == '\n') {
            newLine();
        } else {
            currentChar++; // This should probably be done before checking for a space.
            if (character == ' ') {
                newToken();
            }
        }
    }

    private void newLine() {
        currentLine++;
        currentChar = 1;
    }

    // Start reading a new token.
    private void newToken() {
        lineStart = currentLine;
        charStart = currentChar;
    }
}
