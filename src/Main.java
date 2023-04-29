import java.util.ArrayList;
import java.util.List;
import parse.symbol.SymbolTable;
import parse.tree.Statement;
import util.Lex;
import util.Parser;

public class Main {
    public static void main(String[] args) {
        Lex lex = new Lex("resources/program.exe");
        SymbolTable symbolTable = new SymbolTable();
        List<Statement> statements = new ArrayList<>();

        Parser parser = new Parser(lex, symbolTable, statements);
        parser.parse();
        lex.finalize();

        lex.printTokens();
        symbolTable.print();
        parser.print();
    }
}
