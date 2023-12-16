package interpreter;

import java.util.Scanner;

import interpreter.lexer.Lexer;
import interpreter.parser.Parser;
import interpreter.parser.UnexpectedTokenException;
import interpreter.statement.Statement;

public final class Interpreter {
    private final Parser parser;
    private final Lexer lexer;
    private final String prompt = ">>> ";

    public Interpreter(String source) {
        lexer = new Lexer(source);
        parser = new Parser(lexer);
    }

    public void run() {
        try {
            Statement statement;
            while ((statement = parser.parse()) != null)
                statement.exec();
        } catch (UnexpectedTokenException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void repl() {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        while (true) {
            System.out.print(prompt);
            line = scanner.nextLine();
            if (line.equals("exit"))
                break;
            lexer.append(line);
            run();
        }

        scanner.close();
    }
}
