package com.epam.rd.autocode.iterator;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class IteratorsTest {

    @ParameterizedTest
    @MethodSource("regularCases")
    public void testRegularCases(int times, int[] array, String expected) {
        List<Integer> sink = new ArrayList<>();
        final Iterator<Integer> it = Iterators.intArrayXTimesIterator(array, times);
        while (it.hasNext()) {
            sink.add(it.next());
        }
        assertEquals(expected, sink.toString());
    }

    public static Stream<Arguments> regularCases() {
        return Stream.of(
                arguments(2, new int[]{1, 2, 3}, "[1, 1, 2, 2, 3, 3]"),
                arguments(2, new int[]{5, 0, -5, 0}, "[5, 5, 0, 0, -5, -5, 0, 0]"),
                arguments(2, new int[]{}, "[]"),
                arguments(2, new int[]{1, 1, 1, 1, 0, 1}, "[1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1]"),

                arguments(3, new int[]{1, 2, 3}, "[1, 1, 1, 2, 2, 2, 3, 3, 3]"),
                arguments(3, new int[]{5, 0, -5, 0}, "[5, 5, 5, 0, 0, 0, -5, -5, -5, 0, 0, 0]"),
                arguments(3, new int[]{}, "[]"),
                arguments(3, new int[]{1, 1, 1, 1, 0, 1}, "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1]"),

                arguments(5, new int[]{1, 2, 3}, "[1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3]"),
                arguments(5, new int[]{5, 0, -5, 0}, "[5, 5, 5, 5, 5, 0, 0, 0, 0, 0, -5, -5, -5, -5, -5, 0, 0, 0, 0, 0]"),
                arguments(5, new int[]{}, "[]"),
                arguments(5, new int[]{1, 1, 1, 1, 0, 1}, "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1]")
        );
    }

    @Test
    void testEmptyArray() {
        assertAll(
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> Iterators.intArrayXTimesIterator(new int[]{}, 1).next()),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> Iterators.intArrayXTimesIterator(new int[]{}, 4).next()),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> Iterators.intArrayXTimesIterator(new int[]{}, 10).next()),
                () -> assertThrows(
                        NoSuchElementException.class,
                        () -> Iterators.intArrayXTimesIterator(new int[]{}, 2).next())
        );
    }

    @Test
    void testBadTimes() {
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> Iterators.intArrayXTimesIterator(new int[]{4}, -11)),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> Iterators.intArrayXTimesIterator(new int[]{2}, 0)),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> Iterators.intArrayXTimesIterator(new int[]{4, 5, 6}, -1)),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> Iterators.intArrayXTimesIterator(new int[]{1}, -7))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 10, 15, 20})
    void testSingleElementArray(int times) {
        final Iterator<Integer> itr = Iterators.intArrayXTimesIterator(new int[]{1}, times);
        for (int i = 0; i < times; i++) {
            itr.next();
        }
        assertThrows(NoSuchElementException.class, () -> itr.next());
    }
}