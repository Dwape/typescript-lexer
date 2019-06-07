package parser;

import lexer.*;
import lexer.token.TokenStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void typeNotDeclared() {
        // We do not support type inference.
        Lexer lexer = new TokenLexer();
        Parser parser = new TypeScriptParser();
        InputStream stream = new TextStream("let nice = 4;");
        TokenStream tokens = lexer.lex(stream);
        assertThrows(SyntaxErrorException.class, () -> parser.parse(tokens));
    }

    @Test
    public void unexpectedEndOfInput() {
        Lexer lexer = new TokenLexer();
        Parser parser = new TypeScriptParser();
        InputStream stream = new TextStream("let nice: string = ");
        TokenStream tokens = lexer.lex(stream);
        assertThrows(SyntaxErrorException.class, () -> parser.parse(tokens));
    }

}
