package lexer;

public class TypeScriptToken implements Token{

    private String content;

    private TokenType type; // Type would probably be more useful as an enum

    public TypeScriptToken(String content, TokenType type){
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public TokenType getType() {
        return this.type;
    }
}
