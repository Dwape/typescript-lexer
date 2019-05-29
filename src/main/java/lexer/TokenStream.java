package lexer;

public interface TokenStream {

    public Token peek();

    public void consume();

    public boolean hasNext();
}
