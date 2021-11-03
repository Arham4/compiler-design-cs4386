import java.io.*;
import java_cup.runtime.*;

public final class LexerTest {
    public static void main(String[] args) {
        Symbol sym;
        try {
            Grammar grammar = new Grammar(new FileReader(args[0]));
            for (sym = grammar.next_token(); sym.sym != 0;
                    sym = grammar.next_token()) {

                System.out.println("Token " + sym +
                    ", with value = " + sym.value +
                    "; at line " + sym.left + ", column " + sym.right);

            }
        }
        catch (Exception e) {
        }
    }
}
