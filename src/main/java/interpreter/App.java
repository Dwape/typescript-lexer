package interpreter;

import lexer.*;
import parser.Parser;
import parser.TypeScriptParser;
import parser.nodes.ASTNode;

public class App {

    public static void main(String[] args) {
        Lexer lexer = new TokenLexer();
        //InputStream stream = new TextStream("let nice: number = 42; nice = 12; print(nice*2+3);");
        //InputStream stream = new TextStream("let nice: string = 'hello'; nice = nice + 4*5; print(nice);");
        InputStream stream = new TextStream("let nice: string; nice = 'hello' + 4*2; print(nice);"); // Throws SyntaxError.
        //InputStream stream = new TextStream("let nice: string = 'hello'; nice = 4; print(nice);");
        //InputStream stream = new TextStream("let nice: string = '4'; print(hello);");
        TokenStream output = lexer.lex(stream);

        Parser parser = new TypeScriptParser();
        ASTNode node = parser.parse(output);

        // stream.peek(); // This should break.
        // There is a problem here
        // The states consume an extra token, that must be fixed.

        TestVisitor interpreter = new TestVisitor();
        interpreter.start(node);

        //System.out.println("why did it fail?");
    }
}
