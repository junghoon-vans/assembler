import symbol.SymbolTable;
import component.Lex;
import component.Parser;

public class Main {
    public static void main(String[] args) {
        Lex lex = new Lex("resources/program.exe");
        SymbolTable symbolTable = new SymbolTable();

        Parser parser = new Parser(lex, symbolTable);
        parser.parse();
        lex.finalize();

        symbolTable.print();
        parser.print();
    }
}
