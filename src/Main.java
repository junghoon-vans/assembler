import java.util.ArrayList;
import java.util.List;
import parse.symbol.SymbolTable;
import parse.tree.Statement;
import util.CodeGenerator;
import util.Lex;
import util.Parser;

public class Main {
    public static void main(String[] args) {
        Lex lex = new Lex("resources/program.asm");
        SymbolTable symbolTable = new SymbolTable();
        List<Statement> statements = new ArrayList<>();

        Parser parser = new Parser(lex, symbolTable, statements);
        parser.parse();
        lex.finalize();

        CodeGenerator codeGenerator = new CodeGenerator("resources/program.exe");
        codeGenerator.generate(symbolTable, statements);

        lex.printTokens();
        symbolTable.print();
        parser.print();
    }
}
