package com.epam.rd.autotasks.max;

import java.util.OptionalInt;

public class MaxMethod {
    public static OptionalInt max(int[] values) {
        if (values == null) { return OptionalInt.empty(); }
        if (values.length == 0)  { return OptionalInt.empty(); }

        int max = Integer.MIN_VALUE;
        for(int n : values) {
            if (n > max) {
                max = n;
            }
        }
        return OptionalInt.of(max);
        //throw new UnsupportedOperationException();
    }
}
