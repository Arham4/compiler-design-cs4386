package tokens.methods.args;

import information.FieldInformation;
import information.MethodInformation;
import tokens.NonTerminalToken;
import tokens.expr.Expr;
import tokens.lexeme.Type;
import tokens.lexeme.Types;
import type_checking.TypeCheckException;
import type_checking.TypeCheckable;

import java.util.Map;

public final class PrintList implements NonTerminalToken, TypeCheckable<Void> {
    public static class Builder {
        private Expr expr;
        private PrintList printList = null;

        public Builder expr(Expr expr) {
            this.expr = expr;
            return this;
        }

        public Builder printList(PrintList printList) {
            this.printList = printList;
            return this;
        }

        public PrintList build() {
            return new PrintList(expr, printList);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final Expr expr;
    private final PrintList printList;

    private PrintList(Expr expr, PrintList printList) {
        this.expr = expr;
        this.printList = printList;
    }

    @Override
    public String asString(int tabs) {
        return expr.asString(tabs) + (printList == null ? "" : ", " + printList.asString(tabs));
    }

    @Override
    public Void typeCheck(int scope, Map<String, FieldInformation> fieldSymbolTable, Map<String, MethodInformation> methodSymbolTable) throws TypeCheckException {
        Type exprType = expr.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        if (exprType.isArray()) {
            throw TypeCheckException.withFault("Cannot call print stmt on expr with array type");
        }
        if (exprType == Types.VOID) {
            throw TypeCheckException.withFault("Cannot call print stmt on expr with void type");
        }
        if (printList != null) {
            printList.typeCheck(scope, fieldSymbolTable, methodSymbolTable);
        }
        return null;
    }
}
