package lexer;

import java.util.ArrayList;
import java.util.List;

public class KeywordChecker {

    private List<String> keywords;

    public KeywordChecker(){
        List<String> keywords = new ArrayList<>();
        // This should be defined somewhere else, of course.
        keywords.add("let");
        keywords.add("string");
        keywords.add("number");
        keywords.add("print");
        this.keywords = keywords;
    }

    public boolean check(String identifier) {
        return this.keywords.contains(identifier);
    }
}
