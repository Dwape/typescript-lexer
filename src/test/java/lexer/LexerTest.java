package lexer;

import lexer.token.TokenStream;
import org.junit.jupiter.api.Test;

public class LexerTest {

    @Test
    public void doesNotEndInDelimiter() {
        // We would need to have an end of file, right?
        // That should be the only problem honestly.
        Lexer lexer = new TokenLexer();
        InputStream stream = new TextStream("let nice: string = 4");
        TokenStream tokens = lexer.lex(stream);
        System.out.println("Test");
    }
}
