package interpreter;

import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        Interpreter interpreter;
        if (args.length > 0) {
            String source = "";
            try {
                for (String arg : args)
                    source += new String(Files.readAllBytes(Paths.get(arg))) + "\n";
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            interpreter = new Interpreter(source);
            interpreter.run();
        } else {
            interpreter = new Interpreter("");
        }
        interpreter.repl();
    }
}
