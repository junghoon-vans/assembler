import parse.symbol.SymbolTable;
import util.Lex;
import util.Parser;

public class Main {
    public static void main(String[] args) {
        Lex lex = new Lex("resources/program.exe");
        SymbolTable symbolTable = new SymbolTable();

        Parser parser = new Parser(lex, symbolTable);
        parser.parse();
        lex.finalize();

        lex.printTokens();
        symbolTable.print();
        parser.print();
    }
}
