package lexer.token;

public class TypeScriptToken implements Token{

    private String content;

    private TokenType type; // Type would probably be more useful as an enum

    private TokenPosition position;

    public TypeScriptToken(String content, TokenType type){
        this.content = content;
        this.type = type;
    }

    // Adds extra information to the token.
    // This information is added by the lexer on top of the previous information.
    public void addLocation(TokenPosition position) {
        this.position = position;
    }

    public String getContent() {
        return this.content;
    }

    public TokenType getType() {
        return this.type;
    }

    @Override
    public TokenPosition getPosition() {
        return position;
    }

    // Only for visualization, remove
    public String toString() {
        return "<" + type.toString() + ":" + content + "(" + position.getLine() + "," + position.getCharStart() + ")>";
    }
}
