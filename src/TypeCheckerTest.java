import tokens.Program;
import type_checking.TypeCheckException;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class TypeCheckerTest {

    public static void main(String[] args) throws Exception {
        Reader reader = null;

        if (args.length == 1) {
            File input = new File(args[0]);
            if (!input.canRead()) {
                System.out.println("Error: could not read [" + input + "]");
            }
            reader = new FileReader(input);
        } else {
            reader = new InputStreamReader(System.in);
        }

        Grammar scanner = new Grammar(reader);   // create scanner

        parser parser = new parser(scanner); // create parser
        Program program = null;

        try {
            program = (Program) parser.parse().value;
            //program = (Program) parser.debug_parse().value;  // parse
            //The above line of code will output current state and what token is being processed
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print(program.asString(0));
        //Now test type checking
        try {
            program.typeCheck();
            System.out.println("Type checking complete!");
        } catch (TypeCheckException e) {
            e.printStackTrace();
        }
    }

    public static void error(String s) {
        System.out.println(s);
    }
}
