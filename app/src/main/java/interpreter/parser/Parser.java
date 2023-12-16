package interpreter.parser;

import interpreter.expression.Expression;
import interpreter.expression.Identifier;
import interpreter.expression.Literal;
import interpreter.expression.Operator;
import interpreter.expression.BinaryExpression;
import interpreter.lexer.Lexer;
import interpreter.lexer.Token;
import interpreter.lexer.TokenType;
import interpreter.lexer.UnexpectedCharacterException;
import interpreter.statement.Assignment;
import interpreter.statement.Print;
import interpreter.statement.Statement;

public final class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Statement parse() throws UnexpectedTokenException {
        Token token = getToken();
        TokenType type = token.getType();

        return switch (type) {
            case IDENTIFIER -> parseAssignment(token);
            case PRINT -> parsePrint();
            case EOF -> null;
            case NEWLINE -> parse();
            default -> throw new UnexpectedTokenException(token);
        };

    }

    private Token getToken() {
        try {
            return lexer.next();
        } catch (UnexpectedCharacterException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null; // unreachable
    }

    private void expect(TokenType type) throws UnexpectedTokenException {
        Token token = getToken();

        if (token.getType() != type)
            throw new UnexpectedTokenException(token);
    }

    private Statement parseAssignment(Token token) throws UnexpectedTokenException {
        char identifier = token.getValue();
        expect(TokenType.ASSIGN);
        return new Assignment(identifier, parseExpression());
    }

    private Statement parsePrint() throws UnexpectedTokenException {
        return new Print(parseExpression());
    }

    private Expression parseExpression() throws UnexpectedTokenException {
        Token token = getToken();
        TokenType type = token.getType();

        Expression left = switch (type) {
            case IDENTIFIER -> new Identifier(token.getValue());
            case INTEGER -> new Literal((int) token.getValue() - '0');
            default -> throw new UnexpectedTokenException(token);
        };

        token = getToken();
        type = token.getType();

        if (type == TokenType.EOF || type == TokenType.NEWLINE)
            return left;

        Operator operator = switch (type) {
            case PLUS -> Operator.ADD;
            case MINUS -> Operator.SUBTRACT;
            case MULT -> Operator.MULTIPLY;
            case DIV -> Operator.DIVIDE;
            default -> throw new UnexpectedTokenException(token);
        };

        Expression right = parseExpression();

        return new BinaryExpression(left, right, operator);
    }
}
