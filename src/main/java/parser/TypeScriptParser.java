package parser;

import lexer.token.TokenStream;
import parser.nodes.ASTNode;

// This is not really necessary, it is the same as the ParserState
// The two classes could be combined.
public class TypeScriptParser implements Parser {

    private ParserState state;

    public TypeScriptParser() {
        this.state = new ProgramState();
    }

    @Override
    public ASTNode parse(TokenStream stream) {
        return state.parse(stream);
    }
}
