package main;

import interpreter.Interpreter;
import interpreter.ReferenceError;
import interpreter.TypeError;
import interpreter.TypeScriptInterpreter;
import lexer.*;
import lexer.token.TokenStream;
import parser.Parser;
import parser.SyntaxErrorException;
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

        try {
            InputStream stream = new TextStream(text);

            TokenStream output = lexer.lex(stream);

            Parser parser = new TypeScriptParser();
            ASTNode node = parser.parse(output);

            Interpreter interpreter = new TypeScriptInterpreter();
            interpreter.interpret(node);
        } catch (InvalidInputException error) {
            System.out.println(String.format("Invalid input: %s", error.getMessage()));
        } catch (SyntaxErrorException error) {
            System.out.println(String.format("Syntax error: %s", error.getMessage()));
        } catch (ReferenceError error) {
            System.out.println(String.format("Reference error: %s", error.getMessage()));
        } catch (TypeError error) {
            System.out.println(String.format("Type error: %s", error.getMessage()));
        }

    }

    private static String readFile(String[] args) throws IOException {
        String filePath = "src/main/resources/program.txt";
        if(args.length > 0) filePath = args[0];
        return new String(Files.readAllBytes(Paths.get(filePath)));

    }
}
