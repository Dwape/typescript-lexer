package lexer;

public enum TokenType {
    KEYWORD,
    IDENTIFIER,
    STRING, // Could be called a string literal, but I don't see the need
    NUMBER, // Number literal?
    PLUS,
    MINUS,
    MULTIPLICATION, // These names could change, they are not that consistent.
    DIVISION,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    EQUALS,
    COLON,
    SEMI_COLON
}