package tokens.methods.args.argdecls;

import tokens.NonTerminalToken;
import tokens.lexeme.Type;
import type_checking.TypeCheckable;

public interface ArgDecl extends NonTerminalToken, TypeCheckable<Void> {
    Type getType();
}
