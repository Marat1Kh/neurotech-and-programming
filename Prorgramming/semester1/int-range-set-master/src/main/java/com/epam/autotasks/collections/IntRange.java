package com.epam.autotasks.collections;

import java.util.Objects;
//aaaa
public final class IntRange {
    private final int from;
    private final int to;

    static IntRange range(final int from, final int to) {
        return new IntRange(from, to);
    }

    IntRange(final int from, final int to) {
        if (from > to) {
            throw new IllegalArgumentException("Bad Range values: [" + from + "; " + to + ")");
        }
        this.from = from;
        this.to = to;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public boolean isEmpty() {
        return from == to;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final IntRange intRange = (IntRange) o;
        return from == intRange.from && to == intRange.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "[" + from + "; " + to + ")";
    }
}
