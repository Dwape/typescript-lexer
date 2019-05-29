package lexer;

public class TypeScriptToken implements Token{

    private String content;

    private String type; // Type would probably be more useful as an enum

    public TypeScriptToken(String content, String type){
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public String getType() {
        return this.type;
    }
}
