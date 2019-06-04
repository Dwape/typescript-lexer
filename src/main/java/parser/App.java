package parser;

import lexer.*;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        Lexer lexer = new TokenLexer();
        InputStream stream = new TextStream("4*5/4;");
        TokenStream output = lexer.lex(stream);

        TermState termState = new TermState();
        TermNode node = termState.parse(output);

        System.out.println("why did it fail?");
    }
}