package lexer;

import lexer.token.TokenStream;
import lexer.token.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {

    @Test
    public void doesNotEndInDelimiter() {
        // We would need to have an end of file, right?
        // That should be the only problem honestly.
        Lexer lexer = new TokenLexer();
        InputStream stream = new TextStream("let nice: string = 4");
        TokenStream tokens = lexer.lex(stream);

        TokenType[] types = {
                TokenType.LET,
                TokenType.IDENTIFIER,
                TokenType.COLON,
                TokenType.STRING_TYPE,
                TokenType.EQUALS,
                TokenType.NUMBER};
        int i = 0;
        while (tokens.hasNext()) {
            assertEquals(types[i], tokens.peek().getType());
            tokens.consume();
            i++;
        }
        assertFalse(tokens.hasNext());
    }

    @Test
    public void variableStartsWithNumber() {
        Lexer lexer = new TokenLexer();
        InputStream stream = new TextStream("let 5nice: string = 4");
        assertThrows(InvalidInputException.class, () -> lexer.lex(stream));
    }

    @Test
    public void normalStatement() {
        Lexer lexer = new TokenLexer();
        InputStream stream = new TextStream("nice = 3;");
        TokenStream tokens = lexer.lex(stream);

        TokenType[] types = {
                TokenType.IDENTIFIER,
                TokenType.EQUALS,
                TokenType.NUMBER,
                TokenType.SEMI_COLON};
        int i = 0;
        while (tokens.hasNext()) {
            assertEquals(types[i], tokens.peek().getType());
            tokens.consume();
            i++;
        }
        assertFalse(tokens.hasNext());
    }
}
