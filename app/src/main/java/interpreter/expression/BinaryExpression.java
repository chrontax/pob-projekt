package interpreter.expression;

public final class BinaryExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final Operator operator;

    public BinaryExpression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public int eval() {
        int output = 0;

        try {
            output = switch (operator) {
                case ADD -> left.eval() + right.eval();
                case SUBTRACT -> left.eval() - right.eval();
                case MULTIPLY -> left.eval() * right.eval();
                case DIVIDE -> left.eval() / right.eval();
            };
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return output;
    }
}
