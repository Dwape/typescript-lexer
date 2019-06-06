package lexer.token;

public interface TokenStream {

    public Token peek();

    public void consume();

    public boolean hasNext();
}
