package lexer;

import lexer.token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class KeywordChecker {

    private Map<String, TokenType> keywords;

    public KeywordChecker(){
        keywords = new HashMap<>();
        // This should be defined somewhere else, of course.
        keywords.put("let", TokenType.LET);
        keywords.put("string", TokenType.STRING_TYPE);
        keywords.put("number", TokenType.NUMBER_TYPE);
        keywords.put("print", TokenType.PRINT);
    }

    // We should return a token type.
    // What happens when there is no token?
    // We could improve how this works.
    public TokenType check(String identifier) {
        return this.keywords.get(identifier);
    }
}
