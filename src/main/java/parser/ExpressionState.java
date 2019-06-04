package parser;

// Import statements are important for modules
import lexer.TokenStream;

import java.util.Stack;

public class ExpressionState implements ParserState {

    private TermState term;

    private ExpressionNode buffer; // Works are the buffer for the state.

    // How should the structure be created?
    public ExpressionState() {
        this.term = new TermState();
    }

    // Should we tell a state that it is now time to do its work?
    // Should we pass a stack and the input as a parameter?

    // The state should probably work with both a stack and an input stream.
    // It should read either from the stack or from the stream.
    // How do we know when to read from where?

    // Let's say we start reading from the stack.

    // Think of a better name for this method.
    public void parse(TokenStream stream, Stack<ASTNode> stack) {
        // The state should check if in the stack there are elements that fulfill a rule.
        // Expression will be either a concatenation or an addition.

        // The caller will have placed the last value in the stack
        // (This could work differently to be honest).
        ASTNode node = stack.peek();
        // Should we create types for the token types?
        // Or should they be translated to rules directly?

        // In this case, we start with a String (or at least that is what I think).
        if (true) {
        } else { // Otherwise, it should be Multiplication (right?)

        }

        // Does this even make sense? (I'm not sure to be honest).
    }

    @Override
    public ExpressionNode parse(TokenStream stream) {
        term.parse(stream);
        return null; // Remove
    }
}
