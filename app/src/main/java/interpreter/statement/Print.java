package interpreter.statement;

import interpreter.expression.Expression;

public final class Print implements Statement {
    private final Expression expression;

    public Print(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void exec() {
        System.out.println(expression.eval());
    }
}
