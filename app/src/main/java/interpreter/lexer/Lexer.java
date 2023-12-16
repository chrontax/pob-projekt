package interpreter.lexer;

public final class Lexer {
    private String input;
    private int position = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public void append(String input) {
        this.input += input;
    }

    public Token next() throws UnexpectedCharacterException {
        if (position >= input.length())
            return new Token(TokenType.EOF);

        char c = input.charAt(position++);

        switch (c) {
            case 'p':
                return new Token(TokenType.PRINT);
            case '=':
                return new Token(TokenType.ASSIGN);
            case '+':
                return new Token(TokenType.PLUS);
            case '-':
                return new Token(TokenType.MINUS);
            case '*':
                return new Token(TokenType.MULT);
            case '/':
                return new Token(TokenType.DIV);
            case '\n':
                return new Token(TokenType.NEWLINE);
            case '"':
                return new Token(TokenType.QUOTE);
        }

        if (Character.isDigit(c))
            return new Token(TokenType.INTEGER, c);
        else if (Character.isLetter(c))
            return new Token(TokenType.IDENTIFIER, c);

        throw new UnexpectedCharacterException(c);
    }
}
