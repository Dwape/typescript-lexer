package lexer;

public interface Detector {

    public TokenOutput detect(InputStream stream);
}
