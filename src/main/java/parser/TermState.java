package parser;

import lexer.Token;
import lexer.TokenStream;
import lexer.TokenType;

public class TermState implements ParserState{

    private TermNode buffer;

    public TermState() {
        this.buffer = null; // Is this even necessary?
    }

    @Override
    public TermNode parse(TokenStream stream) {
        // If the stream is empty, let's just return the buffer
        if (!stream.hasNext()) {
            return buffer;
        }
        // If there is nothing in the buffer, we must read the next stream token.
        // This token could be three things, a number, an identifier or a string.
        if (buffer == null) {
            LiteralNode node = readFromStream(stream);
            // When the node only has one child, it is neither multiplication nor division
            // There could be a third node type with only one child for this purpose.
            buffer = new TermSingleNode(node);
            // Now we do a recursive call, and hope this works.
            return parse(stream);
        }

        // Now for the case where there is already something in the buffer
        // There are two cases

        // First case, the next token in the stream is  * or /
        // This could probably be its own method (if we wanted to make it nicer).
        Token token = stream.peek();
        if (token.getType() == TokenType.MULTIPLICATION) {
            // Now we need to read from the stream to know what to do.
            stream.consume();
            LiteralNode node = readFromStream(stream);
            buffer = new MultiplicationNode(buffer, node);
            return parse(stream);
        }

        if (token.getType() == TokenType.DIVISION) {
            stream.consume();
            LiteralNode node = readFromStream(stream);
            buffer = new DivisionNode(buffer, node);
            return parse(stream);
        }

        // It wasn't a multiplication, we need to return
        TermNode result = buffer;
        reset();
        return result;
    }

    private void reset() {
        buffer = null;
    }

    private LiteralNode readFromStream(TokenStream stream) {
        Token token = stream.peek();
        stream.consume(); // We should always be able to consume the character.
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
