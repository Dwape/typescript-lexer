package interpreter;

import lexer.InputStream;
import lexer.Lexer;
import lexer.TextStream;
import lexer.TokenLexer;
import lexer.token.TokenStream;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.TypeScriptParser;
import parser.nodes.ASTNode;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InterpreterTest {

    @Test
    public void invalidType() {
        Lexer lexer = new TokenLexer();
        Parser parser = new TypeScriptParser();
        Interpreter interpreter = new TypeScriptInterpreter();
        InputStream stream = new TextStream("let nice: string = 4;");
        TokenStream tokens = lexer.lex(stream);
        ASTNode tree = parser.parse(tokens);
        assertThrows(TypeError.class, () -> interpreter.interpret(tree));
    }

    @Test
    public void reassignment() {
        Lexer lexer = new TokenLexer();
        Parser parser = new TypeScriptParser();
        Interpreter interpreter = new TypeScriptInterpreter();
        InputStream stream = new TextStream("let nice: number = 4;\nlet nice: number = 5;");
        TokenStream tokens = lexer.lex(stream);
        ASTNode tree = parser.parse(tokens);
        assertThrows(ReferenceError.class, () -> interpreter.interpret(tree));
    }

    @Test
    public void invalidOperation() {
        Lexer lexer = new TokenLexer();
        Parser parser = new TypeScriptParser();
        Interpreter interpreter = new TypeScriptInterpreter();
        InputStream stream = new TextStream("print('hello'/4);");
        TokenStream tokens = lexer.lex(stream);
        ASTNode tree = parser.parse(tokens);
        assertThrows(TypeError.class, () -> interpreter.interpret(tree));
    }

    @Test
    public void undefinedVariable() {
        Lexer lexer = new TokenLexer();
        Parser parser = new TypeScriptParser();
        Interpreter interpreter = new TypeScriptInterpreter();
        InputStream stream = new TextStream("nice = 4;");
        TokenStream tokens = lexer.lex(stream);
        ASTNode tree = parser.parse(tokens);
        assertThrows(ReferenceError.class, () -> interpreter.interpret(tree));
    }
}
