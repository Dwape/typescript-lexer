package lexer;

import lexer.token.TokenStream;

public interface Lexer {

    public TokenStream lex(InputStream stream);
}
