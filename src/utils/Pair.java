package utils;

public final class Pair<A, B> {
    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<>(first, second);
    }

    private final A first;
    private final B second;

    private Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
