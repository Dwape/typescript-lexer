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
        //InputStream stream = new TextStream("let nice: string; nice = 'hello' + 4*2; print(nice);");
        //InputStream stream = new TextStream("let nice: string = 'hello'; nice = 4; print(nice);");
        //InputStream stream = new TextStream("let nice: string = '4'; print(hello);");
        //InputStream stream = new TextStream("print(4+2+'hello'+6/2);");
        InputStream stream = new TextStream("let nice1: string = 'hello'; let nice2: string=', world!'; let nice3: string = nice1 + nice2; print(nice3);"); // Throws SyntaxError
        TokenStream output = lexer.lex(stream);

        Parser parser = new TypeScriptParser();
        ASTNode node = parser.parse(output);

        // stream.peek(); // This should break.
        // There is a problem here
        // The states consume an extra token, that must be fixed.

        InterpreterVisitor interpreter = new InterpreterVisitor();
        interpreter.start(node);

        //System.out.println("why did it fail?");
    }
}
