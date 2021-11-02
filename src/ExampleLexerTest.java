import java.io.*;
import java_cup.runtime.*;

public class ExampleLexerTest{

    public static void main(String[] args) {
        Symbol sym;
        try {
            ExampleLexer lexer = new ExampleLexer(new FileReader(args[0]));
            for (sym = lexer.next_token(); sym.sym != 0;
                    sym = lexer.next_token()) {

                System.out.println("Token " + sym +
                    ", with value = " + sym.value + 
                    "; at line " + sym.left + ", column " + sym.right);

            }
        }
        catch (Exception e) {
        }
    }
}
