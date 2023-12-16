package interpreter.expression;

import interpreter.Context;

public final class Identifier implements Expression {
    private final char identifier;

    public Identifier(char identifier) {
        this.identifier = identifier;
    }

    @Override
    public int eval() {
        return Context.getInstance().getVariable(identifier);
    }
}
