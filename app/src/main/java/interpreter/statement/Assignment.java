package interpreter.statement;

import interpreter.expression.Expression;
import interpreter.Context;

public final class Assignment implements Statement {
    private final char identifier;
    private final Expression expression;

    public Assignment(char identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public void exec() {
        Context.getInstance().setVariable(identifier, expression.eval());
    }
}
