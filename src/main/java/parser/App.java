package parser;

import lexer.*;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        Lexer lexer = new TokenLexer();
        InputStream stream = new TextStream("4*5+2/1+19;");
        TokenStream output = lexer.lex(stream);

        ExpressionState expressionState = new ExpressionState();
        ExpressionNode node = expressionState.parse(output);

        // stream.peek(); // This should break.
        // There is a problem here
        // The states consume an extra token, that must be fixed.

        System.out.println("why did it fail?");
    }
}