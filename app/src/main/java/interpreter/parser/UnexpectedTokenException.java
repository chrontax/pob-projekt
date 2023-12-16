package interpreter.parser;

import interpreter.lexer.Token;

public final class UnexpectedTokenException extends Exception {
    public UnexpectedTokenException(Token token) {
        super("Unexpected token: " + token);
    }
}
