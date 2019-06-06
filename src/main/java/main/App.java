package main;

import interpreter.Interpreter;
import interpreter.TypeScriptInterpreter;
import lexer.InputStream;
import lexer.Lexer;
import lexer.TextStream;
import lexer.TokenLexer;
import lexer.token.TokenStream;
import parser.Parser;
import parser.TypeScriptParser;
import parser.nodes.ASTNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) {
        Lexer lexer = new TokenLexer();

        String text;
        try {
            text = readFile(args);
        } catch (IOException error) {
            System.out.println(error.getMessage());
            return;
        }

        InputStream stream = new TextStream(text);

        TokenStream output = lexer.lex(stream);

        Parser parser = new TypeScriptParser();
        ASTNode node = parser.parse(output);

        Interpreter interpreter = new TypeScriptInterpreter();
        interpreter.interpret(node);
    }

    private static String readFile(String[] args) throws IOException {
        String filePath = "src/main/resources/program.txt";
        if(args.length > 0) filePath = args[0];
        return new String(Files.readAllBytes(Paths.get(filePath)));

    }
}
