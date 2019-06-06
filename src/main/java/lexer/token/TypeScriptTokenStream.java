package lexer.token;

import java.util.List;

public class TypeScriptTokenStream implements TokenStream{

    private List<Token> tokens;

    private int position;

    public TypeScriptTokenStream(List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
    }

    @Override
    public Token peek() {
        return tokens.get(position);
    }

    @Override
    public void consume() {
        position++;
    }

    @Override
    public boolean hasNext() {
        return tokens.size() > position; // Or something like that.
    }
}
