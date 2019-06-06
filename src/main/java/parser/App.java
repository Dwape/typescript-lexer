package parser;

import lexer.*;
import lexer.token.TokenStream;
import parser.nodes.ASTNode;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        Lexer lexer = new TokenLexer();
        //InputStream stream = new TextStream("4*5+2/1+19;");
        InputStream stream = new TextStream("print(3+4);let nice: number = 23; nice = 24;");
        TokenStream output = lexer.lex(stream);

        Parser parser = new TypeScriptParser();
        ASTNode node = parser.parse(output);

        // stream.peek(); // This should break.
        // There is a problem here
        // The states consume an extra token, that must be fixed.

        System.out.println("why did it fail?");
    }
}