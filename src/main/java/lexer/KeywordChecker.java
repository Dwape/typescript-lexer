package lexer;

import java.util.List;

public class KeywordChecker {

    private List<String> keywords;

    public KeywordChecker(List<String> keywords){
        this.keywords = keywords;
    }

    public boolean check(String identifier) {
        return this.keywords.contains(identifier);
    }
}
