package com.epam.rd.autocode.iterator;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class IteratorsTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testCase(final String[] columns, final int[] rows, final String expected) {
        final Iterable<String> table = Iterators.table(columns, rows);

        List<String> sink = new ArrayList<>();
        for (String cell : table) {
            sink.add(cell);
        }
        assertEquals(expected, sink.toString());
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                arguments("a,b,c,d,e,f,g,h,i".split(","), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        "[a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10]"),
                arguments("3,6,9".split(","), new int[]{1, 2, 3, 4, 5},
                        "[31, 32, 33, 34, 35, 61, 62, 63, 64, 65, 91, 92, 93, 94, 95]"),
                arguments("Nikolai,Alexander,Pyotr".split(","), new int[]{1, 2},
                        "[Nikolai1, Nikolai2, Alexander1, Alexander2, Pyotr1, Pyotr2]"));
    }
}