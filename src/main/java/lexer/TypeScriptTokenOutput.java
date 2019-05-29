package lexer;

import java.util.ArrayList;
import java.util.List;

public class TypeScriptTokenOutput implements TokenOutput{

    private List<Token> tokens;

    public TypeScriptTokenOutput() {
        this.tokens = new ArrayList<>();
    }

    public void writeToken(Token token) {
        this.tokens.add(token);
    }
}
