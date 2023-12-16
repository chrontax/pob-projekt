package interpreter;

import java.util.HashMap;

public final class Context {
    private static final ContextInstance instance = new ContextInstance();

    public static ContextInstance getInstance() {
        return instance;
    }

    public static final class ContextInstance {
        private final HashMap<Character, Integer> variables = new HashMap<>();

        public void setVariable(char name, int value) {
            variables.put(name, value);
        }

        public int getVariable(char name) {
            return variables.get(name);
        }
    }
}
