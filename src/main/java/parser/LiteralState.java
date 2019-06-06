package parser;

import lexer.token.Token;
import lexer.token.TokenStream;
import lexer.token.TokenType;
import parser.nodes.IdentifierNode;
import parser.nodes.LiteralNode;
import parser.nodes.NumberNode;
import parser.nodes.StringNode;

public class LiteralState implements ParserState {

    public LiteralState() {}

    @Override
    public LiteralNode parse(TokenStream stream) {
        Token token = stream.peek();
        stream.consume(); // We should always be able to consume the token, right?.
        if (token.getType() == TokenType.IDENTIFIER)
            return new IdentifierNode(token.getContent());
        if (token.getType() == TokenType.STRING)
            return new StringNode(token.getContent());
        if (token.getType() == TokenType.NUMBER)
            return new NumberNode(token.getContent());
        // Something went horribly wrong, throw an exception.
        throw new SyntaxErrorException();
    }
}
