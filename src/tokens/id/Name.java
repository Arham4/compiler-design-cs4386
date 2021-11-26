package tokens.id;

import tokens.NonTerminalToken;
import tokens.expr.Expr;

public interface Name extends NonTerminalToken {
    static Name simple(String id) {
        return new Name() {
            @Override
            public String asString(int tabs) {
                return id;
            }

            @Override
            public String getId() {
                return id;
            }
        };
    }

    static Name array(String id, Expr expr) {
        return new Name() {
            @Override
            public String asString(int tabs) {
                return id + "[" + expr.asString(tabs) + "]";
            }

            @Override
            public String getId() {
                return id;
            }
        };
    }

    String getId();
}
