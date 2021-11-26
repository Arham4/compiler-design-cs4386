# CS 4386 - Project 3: Symbol Table & Type Checking

This codebase uses Cup and JFlex to generate an abstract syntax tree that follows rules for terminals and nonterminals 
that are given. Then, the program constructs a symbol table and uses it to type check the syntax.

This was made for CS 4386 (Compiler Design) at the University of Texas at Dallas, taught by Dr. Shiyi Wei.

## Instructions

1. Find the Makefile that corresponds to your system. Solaris will use the Makefile-linux file.
2. Rename the Makefile to just say `Makefile`.
3. Open the terminal to the current directory.
4. Type `make`. This will generate the required classes, compile it to the `out/production/project-3` directory, then run program.
5. To change the test file, open the Makefile and change the `TEST_FILE` constant to be the respective test.
6. To change the input directory, open the Makefile and change the `TEST_FILES_DIR` constant to be the respective directory.
7. To change the output directory, open the Makefile and change the `OUTPUT_FILES_DIR` constant to be the respective directory.

Note: `make runParser` may be executed to run the abstract syntax tree parser and `make runLexer` may be executed to run the Lexical analyser.

## Grammar Implemented

![part 1](https://i.imgur.com/v9elsbV.png)
![part 2](https://i.imgur.com/NelujQF.png)
