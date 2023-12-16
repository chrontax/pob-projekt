package interpreter.expression;

public final class Literal implements Expression {
    private final int value;

    public Literal(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}
