package interpreter.lexer;

public final class Token {
    private final TokenType type;
    private final char value;

    public Token(TokenType type) {
        this(type, '\0');
    }

    public Token(TokenType type, char value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public char getValue() {
        return value;
    }
}
