package lexer.token;

public interface Token {

    public String getContent();

    public TokenType getType();

    public TokenPosition getPosition();

    // Should we add it, should we create a new one, or what?
    public void addLocation(TokenPosition position);
}
