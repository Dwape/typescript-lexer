package parser;

import lexer.TokenStream;

public interface ParserState {

    // This method would be shared everywhere.
    // We should probably change the name
    public ASTNode parse(TokenStream stream); // Would this even work?
}
