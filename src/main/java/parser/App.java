package parser;

import lexer.*;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        Lexer lexer = new TokenLexer();
        InputStream stream = new TextStream("4*5+2/1;");
        TokenStream output = lexer.lex(stream);

        ExpressionState expressionState = new ExpressionState();
        ExpressionNode node = expressionState.parse(output);

        System.out.println("why did it fail?");
    }
}