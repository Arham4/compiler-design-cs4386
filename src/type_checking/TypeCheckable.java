package type_checking;

public interface TypeCheckable<R> {
    R typeCheck() throws TypeCheckException;
}
