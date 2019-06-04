package parser;

import lexer.*;
import parser.nodes.ProgramNode;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        Lexer lexer = new TokenLexer();
        //InputStream stream = new TextStream("4*5+2/1+19;");
        InputStream stream = new TextStream("print(3+4);let nice: number = 23; nice = 24;");
        TokenStream output = lexer.lex(stream);

        ProgramState programState = new ProgramState();
        ProgramNode node = programState.parse(output);

        // stream.peek(); // This should break.
        // There is a problem here
        // The states consume an extra token, that must be fixed.

        System.out.println("why did it fail?");
    }
}