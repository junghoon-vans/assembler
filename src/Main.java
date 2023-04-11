import component.Lex;
import component.Parser;

public class Main {
    public static void main(String[] args) {
        Lex lex = new Lex("resources/program.exe");
        Parser parser = new Parser(lex);
        parser.parse();
        lex.finalize();
    }
}
