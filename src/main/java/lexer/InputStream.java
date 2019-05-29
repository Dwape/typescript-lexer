package lexer;

public interface InputStream {

    // Returns the next character in the input stream
    public char peek();

    public void consume();

    public boolean hasNext();
}
