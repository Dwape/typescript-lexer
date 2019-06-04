package interpreter;

import lexer.*;
import parser.Parser;
import parser.TypeScriptParser;
import parser.nodes.ASTNode;

public class App {

    public static void main(String[] args) {
        Lexer lexer = new TokenLexer();
        InputStream stream = new TextStream("print(10/2+4*5);");
        TokenStream output = lexer.lex(stream);

        Parser parser = new TypeScriptParser();
        ASTNode node = parser.parse(output);

        // stream.peek(); // This should break.
        // There is a problem here
        // The states consume an extra token, that must be fixed.

        TestVisitor interpreter = new TestVisitor();
        interpreter.start(node);

        System.out.println("why did it fail?");
    }
}
