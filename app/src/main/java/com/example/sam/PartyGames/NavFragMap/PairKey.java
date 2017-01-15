package com.example.sam.PartyGames.NavFragMap;

/**
 * Pair of template values
 */

public final class PairKey<A, B> {
    public final A a;
    public final B b;

    private PairKey(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <A, B> PairKey<A, B> make(A a, B b) {
        return new PairKey<A, B>(a, b);
    }

    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) { return false; }
        PairKey that = (PairKey) o;
        return (a == null ? that.a == null : a.equals(that.a))
                && (b == null ? that.b == null : b.equals(that.b));
    }
    public A getFirst() {
        return a;
    }
    public B getSecond() {
        return b;
    }
}