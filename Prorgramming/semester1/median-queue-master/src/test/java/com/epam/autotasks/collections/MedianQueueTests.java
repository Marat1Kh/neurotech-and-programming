package com.epam.autotasks.collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MedianQueueTests {

    @ParameterizedTest
    @MethodSource({"testCases", "randomTestCases"})
    void testMedianQueue(int expectedMedian,
                         String expectedQueue,
                         List<Integer> elements) {
        Queue<Integer> queue = new MedianQueue<>();
        assertEquals(0, queue.size());

        elements.forEach(queue::offer);

        assertEquals(expectedMedian, queue.peek());
        assertEquals(elements.size(), queue.size());
        assertEquals(expectedMedian, queue.peek());
        assertEquals(expectedQueue, pollQueueToString(queue));
        assertEquals(0, queue.size());

        ArrayList<Integer> shuffledElements = new ArrayList<>(elements);
        Collections.shuffle(shuffledElements);
        shuffledElements.forEach(queue::offer);

        assertEquals(expectedMedian, queue.peek(), "Fail on " + shuffledElements);
        assertEquals(elements.size(), queue.size());
        assertEquals(expectedMedian, queue.peek());
        assertEquals(expectedQueue, pollQueueToString(queue));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(0, "0", List.of(0)),
                arguments(0, "0 1", List.of(0, 1)),
                arguments(1, "1 0 2", List.of(0, 1, 2)),
                arguments(1, "1 0 2", List.of(0, 2, 1)),
                arguments(1, "1 0 2", List.of(1, 0, 2)),
                arguments(1, "1 0 2", List.of(1, 2, 0)),
                arguments(1, "1 0 2", List.of(2, 0, 1)),
                arguments(1, "1 0 2", List.of(2, 1, 0)),
                arguments(2, "2 1 3 0 4", List.of(0, 1, 2, 3, 4)),
                arguments(4, "4 5 3 6 2 7 1 8 0 9", List.of(0, 8, 3, 9, 4, 6, 2, 1, 7, 5)),
                arguments(4, "4 5 3 6 2 7 1 8 0 9", List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)),
                arguments(4, "4 3 5 2 6 1 7 0 8", List.of(0, 1, 2, 3, 4, 5, 6, 7, 8))
        );
    }

    static Stream<Arguments> randomTestCases() {
        Random random = new Random(20211125);

        return Stream.of(
                arguments(1, "1 2 1 3 0 3 0 3 -1 3 -1 3 -3 3 -3 3 -3 3 -4 5 -6 5 -8 6 -8 7 -9 7 -9 7", randomInput(random)),
                arguments(1, "1 5 1 5 -2 5 -3 5 -4 6 -4 6 -5 6 -5 6 -6 6 -6 6 -9 7 -9 7 -9 8 -10 8 -10 8", randomInput(random)),
                arguments(0, "0 0 -1 1 -1 1 -2 2 -3 2 -4 2 -4 4 -5 5 -6 5 -7 6 -7 6 -8 7 -9 8 -10 9 -10 9", randomInput(random)),
                arguments(0, "0 3 0 3 -1 3 -1 4 -2 4 -3 4 -3 6 -3 7 -5 7 -5 8 -6 8 -7 8 -8 8 -8 9 -8 9", randomInput(random)),
                arguments(0, "0 0 0 1 -1 1 -1 1 -1 2 -3 2 -3 4 -4 5 -6 5 -7 6 -7 6 -8 6 -10 7 -10 8 -10 8", randomInput(random))
        );
    }

    private static List<Integer> randomInput(final Random random) {
        return random.ints(30, -10, 10)
                .boxed()
                .collect(Collectors.toList());
    }

    private static String pollQueueToString(final Queue<Integer> queue) {
        return Stream.generate(queue::poll)
                .limit(queue.size())
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }
}