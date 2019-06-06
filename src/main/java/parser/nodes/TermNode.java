package parser.nodes;

import parser.NodeVisitor;

// This could actually be an abstract class (think if that is better).
public interface TermNode extends ASTNode {

    void accept(NodeVisitor visitor);
}
