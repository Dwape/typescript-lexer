package lexer;

public class TextStream implements InputStream{

    private String text;
    private int pointer;

    public TextStream(String text) {
        this.text = text;
        this.pointer = 0;
    }

    public char peek() {
        return this.text.charAt(this.pointer);
    }

    public void consume() {
        this.pointer++;
    }

    public boolean hasNext() {
        return this.pointer < this.text.length();
    }

    @Override
    public void append(char character) {
        text += character;
    }
}
