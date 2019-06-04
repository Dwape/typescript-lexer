package parser.nodes;

import parser.NodeVisitor;

// Represents a node in the Abstract Syntax Tree
// Should there be different node types for each Language Element type?
public interface ASTNode {

    void visit(NodeVisitor visitor);
}
