package lexer;

public class TokenDetector implements Detector{

    public TokenDetector() {
        // Should the logic for creating all the states be handled here?
    }

    // Where should the token output be taken from? They all need to have the same instance.

    // This should probably all change, it is a first attempt to get a general idea of how it would work.
    public TokenOutput detect(InputStream stream) {
        TokenOutput output = new TypeScriptTokenOutput();
        DetectorState state = new SpaceState(stream, output);
        DetectorState identifierState = new IdentifierState(output, state, stream);
        DetectorState delimiterState = new DelimiterState(output, state, stream);

        // The regex should probably be defined somewhere else, but where?
        // Who should create all the states and transitions?
        ((SpaceState) state).addTransition(identifierState, new OneCharacterRegex("[a-zA-Z]")); // it must start with a letter.
        ((SpaceState) state).addTransition(delimiterState, new OneCharacterRegex(";|:|\\(|\\)|=|\\+|-|\\*|\\/"));
        while(stream.hasNext()) {
            // Equal state to the next state, returned by process character
            state = state.processCharacter();
        }
        return output;
    }
}
