package parser;

import lexer.Token;
import lexer.TokenStream;
import lexer.TokenType;
import parser.nodes.*;

public class TermState implements ParserState{

    private LiteralState literal;

    private TermNode buffer;

    public TermState() {
        this.buffer = null; // Is this even necessary?
        this.literal = new LiteralState();
    }

    @Override
    public TermNode parse(TokenStream stream) {
        // If the stream is empty, let's just return the buffer
        if (!stream.hasNext()) { // Is this actually necessary?
            return buffer;
        }
        // If there is nothing in the buffer, we must read the next stream token.
        // This token could be three things, a number, an identifier or a string.
        if (buffer == null) {
            LiteralNode node = literal.parse(stream);
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
            LiteralNode node = literal.parse(stream);
            buffer = new MultiplicationNode(buffer, node);
            return parse(stream);
        }

        if (token.getType() == TokenType.DIVISION) {
            stream.consume();
            LiteralNode node = literal.parse(stream);
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
}
