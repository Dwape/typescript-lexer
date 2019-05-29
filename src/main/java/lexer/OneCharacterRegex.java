package lexer;

import java.util.regex.Pattern;

public class OneCharacterRegex implements CharacterChecker{

    private Pattern regex;

    public OneCharacterRegex(String regex){
        this.regex = Pattern.compile(regex);
    }

    @Override
    public boolean check(char character) {
        // String can also match a regex, maybe that is easier to use?
        return this.regex.matcher(String.valueOf(character)).find();
    }
}
