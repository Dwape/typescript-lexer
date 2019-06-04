package parser;

import lexer.TokenStream;
import parser.nodes.ASTNode;

public interface Parser {

    /**
     * Parses the token stream into a tree.
     * @param stream The stream to read the tokens from.
     * @return The Abstract Syntax Tree for the program.
     */
    public ASTNode parse(TokenStream stream);
}
