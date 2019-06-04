package parser;

// Import statements are important for modules
import lexer.Token;
import lexer.TokenStream;
import lexer.TokenType;
import parser.nodes.*;

public class ExpressionState implements ParserState {

    private TermState term;

    private ExpressionNode buffer; // Works are the buffer for the state.

    // How should the structure be created?
    public ExpressionState() {
        this.term = new TermState();
        this.buffer = null; // Necessary?
    }

    @Override
    public ExpressionNode parse(TokenStream stream) {

        // If there is nothing in the buffer, we must read the next stream token.
        // This token could be three things, a number, an identifier or a string.
        if (buffer == null) {
            TermNode node = term.parse(stream); // Asks for the term.
            // When the node only has one child, it is neither multiplication nor division
            // There could be a third node type with only one child for this purpose.
            buffer = new ExpressionSingleNode(node);
            // Now we do a recursive call, and hope this works.
            return parse(stream);
        }

        // Now for the case where there is already something in the buffer
        // There are two cases

        // First case, the next token in the stream is + or -
        // This could probably be its own method (if we wanted to make it nicer).
        Token token = stream.peek();
        if (token.getType() == TokenType.PLUS) {
            // Now we need to read from the stream to know what to do.
            stream.consume();
            TermNode node = term.parse(stream);
            buffer = new AdditionNode(buffer, node);
            return parse(stream);
        }

        if (token.getType() == TokenType.MINUS) {
            stream.consume();
            TermNode node = term.parse(stream);
            buffer = new SubtractionNode(buffer, node);
            return parse(stream);
        }

        // It wasn't a multiplication, we need to return
        ExpressionNode result = buffer;
        reset();
        return result;
    }

    private void reset() {
        buffer = null;
    }
}
