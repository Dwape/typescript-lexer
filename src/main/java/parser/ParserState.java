package parser;

import lexer.token.TokenStream;
import parser.nodes.ASTNode;

public interface ParserState {

    // This method would be shared everywhere.
    // We should probably change the name
    public ASTNode parse(TokenStream stream); // Would this even work?
}
