# CS 4386 - Project 2: Abstract Syntax Tree

This codebase uses Cup and JFlex to generate an abstract syntax tree that follows rules for terminals and nonterminals 
that are given. The goal is to be able to identify that a language is being parsed correctly and that the states are
transitioning in an expected manner. If the abstract syntax tree is correct, then the output should be as expected.

## Instructions

1. Find the Makefile that corresponds to your system. Solaris will use the Makefile-linux file.
2. Rename the Makefile to just say `Makefile`.
3. Open the terminal to the current directory.
4. Type `make`. This will run the `make all` command, which will generate the required classes, compile it to the `out/production/project-1` directory, then run program.
5. To change the test file, open the Makefile and change the `TEST_FILE` constant to be the respective test.
6. To change the input directory, open the Makefile and change the `TEST_FILES_DIR` constant to be the respective directory.
7. To change the output directory, open the Makefile and change the `OUTPUT_FILES_DIR` constant to be the respective directory.

## Grammar Implemented

![part 1](https://i.imgur.com/v9elsbV.png)
![part 2](https://i.imgur.com/NelujQF.png)
