package interpreter.lexer;

public final class UnexpectedCharacterException extends Exception {
    public UnexpectedCharacterException(char c) {
        super("Unexpected character: " + c);
    }
}
