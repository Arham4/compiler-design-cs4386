import java.io.*;
import java_cup.runtime.*;

public class ParserTest {

    public static void main(String[] args) throws Exception {
        Reader reader = null;
        if (args.length == 1) {
            File input = new File(args[0]);
            if (!input.canRead()) {
                System.out.println("Error: could not read ["+input+"]");
            }
            reader = new FileReader(input);
        } else {
            reader = new InputStreamReader(System.in);
        }

        Grammar grammar = new Grammar(reader);   // create grammar

        parser parser = new parser(grammar); // create parser
        Program program = null;

        try {
            program = (Program) parser.parse().value;  // parse
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (program != null) {
            System.out.print(program.asString(0));
        } else {
            System.err.println("There was error reading the program.");
        }
    }
}
