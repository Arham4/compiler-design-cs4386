package tokens.stmts;

import type_checking.TypeCheckable;

public interface Stmt extends TypeCheckable<Void> {
    String asString(String prefix, int tabs);
}
