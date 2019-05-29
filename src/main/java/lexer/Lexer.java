package lexer;

public interface Lexer {

    public TokenStream lex(InputStream stream);
}
