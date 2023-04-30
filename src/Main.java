import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import parse.symbol.SymbolTable;
import parse.tree.Statement;
import util.CodeGenerator;
import util.Lex;
import util.Parser;

public class Main {

    static Lex lex;
    static SymbolTable symbolTable;
    static List<Statement> statements;
    static Parser parser;

    public static void main(String[] args) {
        lex = new Lex("resources/program.asm");
        symbolTable = new SymbolTable();
        statements = new ArrayList<>();

        parser = new Parser(lex, symbolTable, statements);
        parser.parse();
        lex.finalize();

        String option = selectOption();
        execute(option);
    }

    private static String selectOption() {
        System.out.println("1 - Print tokens of lexical analyzer");
        System.out.println("2 - Print symbol table and parse tree");
        System.out.println("3 - Generate code");
        System.out.print("Select an option: ");

        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        sc.close();

        return option;
    }

    private static void execute(String option) {
        System.out.println();

        if (option.equals("1")) {
            lex.printTokens();
        }
        if (option.equals("2")) {
            symbolTable.print();
            parser.print();
        }
        if (option.equals("3")) {
            CodeGenerator codeGenerator = new CodeGenerator("resources/program.exe");
            codeGenerator.generate(symbolTable, statements);
            System.out.println("Code generated successfully!");
        }
    }
}
