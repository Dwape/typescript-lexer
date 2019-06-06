package lexer.token;

public class TypeScriptTokenPosition implements TokenPosition{

    private int line;

    private int charStart;

    public TypeScriptTokenPosition(int line, int charStart) {
        this.line = line;
        this.charStart = charStart;
    }

    public int getLine() {
        return line;
    }

    public int getCharStart() {
        return charStart;
    }
}
