package com.epam.autotasks.collections;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.epam.autotasks.collections.IntRange.range;
import static org.junit.jupiter.api.Assertions.*;

class IntRangeSetTest {

    @Test
    void testInitialState() {
        IntRangeSet set = new IntRangeSet();

        assertEquals(0, set.intsSize());
        assertEquals(0, set.intsSize());

        Iterator<IntRange> iterator = set.iterator();
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    void testAdd() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 5)));

        assertAll(
                () -> assertEquals(5, set.intsSize()),
                () -> assertEquals(List.of(range(0, 5)), toList(set)),
                () -> assertEquals(List.of(0, 1, 2, 3, 4), toIntList(set))
        );
    }

    @Test
    void testAddDuplicates() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 5)));
        assertFalse(set.add(range(0, 5)));
        assertFalse(set.add(range(0, 2)));
        assertFalse(set.add(range(0, 4)));
        assertFalse(set.add(range(4, 5)));
        assertFalse(set.add(range(2, 3)));

        assertAll(
                () -> assertEquals(5, set.intsSize()),
                () -> assertEquals(List.of(range(0, 5)), toList(set)),
                () -> assertEquals(List.of(0, 1, 2, 3, 4), toIntList(set))
        );
    }

    @Test
    void testAddDuplicatesMultiple() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 5)));
        assertTrue(set.add(range(10, 15)));
        assertTrue(set.add(range(5, 10)));

        assertFalse(set.add(range(0, 5)));
        assertFalse(set.add(range(0, 2)));
        assertFalse(set.add(range(0, 4)));
        assertFalse(set.add(range(4, 5)));
        assertFalse(set.add(range(2, 3)));
        assertFalse(set.add(range(2, 3)));

        assertFalse(set.add(range(3, 12)));
        assertFalse(set.add(range(0, 7)));
        assertFalse(set.add(range(7, 15)));
        assertFalse(set.add(range(7, 8)));
        assertFalse(set.add(range(7, 7)));

        assertAll(
                () -> assertEquals(15, set.intsSize()),
                () -> assertEquals(List.of(range(0, 5), range(5, 10), range(10, 15)), toList(set)),
                () -> assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14), toIntList(set))
        );
    }

    @Test
    void testAddIntoMiddle() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 50)));
        assertFalse(set.add(range(10, 40)));
        assertFalse(set.add(range(20, 30)));
        assertFalse(set.add(range(40, 45)));

        assertAll(
                () -> assertEquals(50, set.intsSize()),
                () -> assertEquals("[0; 50)", toString(set))
        );
    }

    @Test
    void testAddToEnd() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 50)));
        assertEquals(50, set.intsSize());
        assertEquals("[0; 50)", toString(set));

        assertTrue(set.add(range(50, 60)));
        assertTrue(set.add(range(60, 70)));
        assertTrue(set.add(range(70, 80)));

        assertAll(
                () -> assertEquals(80, set.intsSize()),
                () -> assertEquals("[0; 50)[50; 60)[60; 70)[70; 80)", toString(set))
        );
    }

    @Test
    void testAddToEndOverlap() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 50)));
        assertEquals(50, set.intsSize());
        assertEquals("[0; 50)", toString(set));

        assertTrue(set.add(range(45, 60)));
        assertTrue(set.add(range(56, 70)));
        assertTrue(set.add(range(67, 80)));

        assertAll(
                () -> assertEquals(80, set.intsSize()),
                () -> assertEquals("[0; 50)[50; 60)[60; 70)[70; 80)", toString(set))
        );
    }

    @Test
    void testAddToEndUnordered() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 50)));
        assertEquals(50, set.intsSize());
        assertEquals("[0; 50)", toString(set));

        assertTrue(set.add(range(60, 70)));
        assertTrue(set.add(range(50, 60)));
        assertTrue(set.add(range(70, 80)));

        assertAll(
                () -> assertEquals(80, set.intsSize()),
                () -> assertEquals("[0; 50)[50; 60)[60; 70)[70; 80)", toString(set))
        );
    }

    @Test
    void testAddToEndOverlapUnordered() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 50)));
        assertEquals(50, set.intsSize());
        assertEquals("[0; 50)", toString(set));

        assertTrue(set.add(range(67, 80)));
        assertTrue(set.add(range(56, 70)));
        assertTrue(set.add(range(45, 60)));

        assertAll(
                () -> assertEquals(80, set.intsSize()),
                () -> assertEquals("[0; 50)[50; 56)[56; 67)[67; 80)", toString(set))
        );
    }

    @Test
    void testAddToStart() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(30, 80)));
        assertEquals(50, set.intsSize());
        assertEquals("[30; 80)", toString(set));

        assertTrue(set.add(range(20, 30)));
        assertTrue(set.add(range(10, 20)));
        assertTrue(set.add(range(0, 10)));

        assertAll(
                () -> assertEquals(80, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 20)[20; 30)[30; 80)", toString(set))
        );
    }

    @Test
    void testAddToStartOverlap() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(30, 80)));
        assertEquals(50, set.intsSize());
        assertEquals("[30; 80)", toString(set));

        assertTrue(set.add(range(20, 38)));
        assertTrue(set.add(range(10, 25)));
        assertTrue(set.add(range(0, 13)));

        assertAll(
                () -> assertEquals(80, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 20)[20; 30)[30; 80)", toString(set))
        );
    }

    @Test
    void testAddToStartUnordered() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(30, 80)));
        assertEquals(50, set.intsSize());
        assertEquals("[30; 80)", toString(set));

        assertTrue(set.add(range(10, 20)));
        assertTrue(set.add(range(0, 10)));
        assertTrue(set.add(range(20, 30)));

        assertAll(
                () -> assertEquals(80, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 20)[20; 30)[30; 80)", toString(set))
        );
    }

    @Test
    void testAddToStartOverlapUnordered() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(30, 80)));
        assertEquals(50, set.intsSize());
        assertEquals("[30; 80)", toString(set));

        assertTrue(set.add(range(10, 25)));
        assertTrue(set.add(range(20, 38)));
        assertTrue(set.add(range(0, 13)));

        assertAll(
                () -> assertEquals(80, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 25)[25; 30)[30; 80)", toString(set))
        );
    }

    @Test
    void testAddBetween() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 10)));
        assertTrue(set.add(range(20, 30)));

        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[20; 30)", toString(set));

        assertTrue(set.add(range(13, 16)));

        assertAll(
                () -> assertEquals(23, set.intsSize()),
                () -> assertEquals("[0; 10)[13; 16)[20; 30)", toString(set))
        );
    }

    @Test
    void testAddBetweenStrict() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 10)));
        assertTrue(set.add(range(20, 30)));

        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[20; 30)", toString(set));

        assertTrue(set.add(range(10, 20)));

        assertAll(
                () -> assertEquals(30, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 20)[20; 30)", toString(set))
        );
    }

    @Test
    void testAddBetweenOverlapBoth() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 10)));
        assertTrue(set.add(range(20, 30)));

        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[20; 30)", toString(set));

        assertTrue(set.add(range(7, 25)));

        assertAll(
                () -> assertEquals(30, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 20)[20; 30)", toString(set))
        );
    }

    @Test
    void testAddBetweenOverlapLeft() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 10)));
        assertTrue(set.add(range(20, 30)));

        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[20; 30)", toString(set));

        assertTrue(set.add(range(7, 20)));

        assertAll(
                () -> assertEquals(30, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 20)[20; 30)", toString(set))
        );
    }

    @Test
    void testAddBetweenOverlapRight() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(0, 10)));
        assertTrue(set.add(range(20, 30)));

        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[20; 30)", toString(set));

        assertTrue(set.add(range(10, 24)));

        assertAll(
                () -> assertEquals(30, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 20)[20; 30)", toString(set))
        );
    }

    @Test
    void testAddFullOverlap() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(20, 50)));

        assertEquals(30, set.intsSize());
        assertEquals("[20; 50)", toString(set));

        assertTrue(set.add(range(0, 100)));

        assertAll(
                () -> assertEquals(100, set.intsSize()),
                () -> assertEquals("[0; 20)[20; 50)[50; 100)", toString(set))
        );

    }

    @Test
    void testAddFullOverlapMultiple() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(20, 25)));
        assertTrue(set.add(range(30, 40)));
        assertTrue(set.add(range(50, 51)));
        assertTrue(set.add(range(78, 92)));

        assertEquals(30, set.intsSize());
        assertEquals("[20; 25)[30; 40)[50; 51)[78; 92)", toString(set));

        assertTrue(set.add(range(0, 100)));

        assertAll(
                () -> assertEquals(100, set.intsSize()),
                () -> assertEquals("[0; 20)[20; 25)[25; 30)[30; 40)[40; 50)[50; 51)[51; 78)[78; 92)[92; 100)", toString(set))
        );
    }

    @Test
    void testAddFullAllOverlaps() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(20, 25)));
        assertTrue(set.add(range(30, 40)));
        assertTrue(set.add(range(50, 51)));
        assertTrue(set.add(range(78, 92)));

        assertTrue(set.add(range(0, 10)));
        assertTrue(set.add(range(95, 100)));

        assertEquals(45, set.intsSize());
        assertEquals("[0; 10)[20; 25)[30; 40)[50; 51)[78; 92)[95; 100)", toString(set));

        assertTrue(set.add(range(5, 98)));


        assertAll(
                () -> assertEquals(100, set.intsSize()),
                () -> assertEquals("[0; 10)[10; 20)[20; 25)[25; 30)[30; 40)[40; 50)[50; 51)[51; 78)[78; 92)[92; 95)[95; 100)", toString(set))
        );
    }

    @Test
    void testRemove() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 100));
        assertEquals(100, set.intsSize());
        assertEquals("[0; 100)", toString(set));

        set.remove(range(0, 100));
        assertAll(
                () -> assertEquals(0, set.intsSize()),
                () -> assertEquals("", toString(set))
        );
    }

    @Test
    void testRemoveSpan() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 50));
        set.add(range(50, 100));
        assertEquals(100, set.intsSize());
        assertEquals("[0; 50)[50; 100)", toString(set));

        set.remove(range(0, 100));
        assertAll(
                () -> assertEquals(0, set.intsSize()),
                () -> assertEquals("", toString(set))
        );
    }

    @Test
    void testRemoveSpan100() {
        IntRangeSet set = new IntRangeSet();

        IntStream.range(0, 100)
                .mapToObj(i -> range(i, i + 1))
                .forEach(set::add);

        String expectedString = IntStream.range(0, 100)
                .mapToObj(i -> range(i, i + 1))
                .map(IntRange::toString)
                .collect(Collectors.joining());

        assertEquals(100, set.intsSize());
        assertEquals(expectedString, toString(set));

        set.remove(range(0, 100));
        assertAll(
                () -> assertEquals(0, set.intsSize()),
                () -> assertEquals("", toString(set))
        );
    }

    @Test
    void testRemoveLeftPart() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 100));
        assertEquals(100, set.intsSize());
        assertEquals("[0; 100)", toString(set));

        set.remove(range(0, 50));
        assertAll(
                () -> assertEquals(50, set.intsSize()),
                () -> assertEquals("[50; 100)", toString(set))
        );
    }

    @Test
    void testRemoveRightPart() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 100));
        assertEquals(100, set.intsSize());
        assertEquals("[0; 100)", toString(set));

        set.remove(range(50, 100));
        assertAll(
                () -> assertEquals(50, set.intsSize()),
                () -> assertEquals("[0; 50)", toString(set))
        );
    }

    @Test
    void testRemoveLeftPartOverlap() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 100));
        assertEquals(100, set.intsSize());
        assertEquals("[0; 100)", toString(set));

        set.remove(range(-100, 50));
        assertAll(
                () -> assertEquals(50, set.intsSize()),
                () -> assertEquals("[50; 100)", toString(set))
        );
    }

    @Test
    void testRemoveRightPartOverlap() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 100));
        assertEquals(100, set.intsSize());
        assertEquals("[0; 100)", toString(set));

        set.remove(range(50, 200));
        assertAll(
                () -> assertEquals(50, set.intsSize()),
                () -> assertEquals("[0; 50)", toString(set))
        );
    }

    @Test
    void testRemoveOverlapBoth() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 100));
        assertEquals(100, set.intsSize());
        assertEquals("[0; 100)", toString(set));

        set.remove(range(-100, 1000));
        assertAll(
                () -> assertEquals(0, set.intsSize()),
                () -> assertEquals("", toString(set))
        );
    }

    @Test
    void testRemoveFromEmptyMiddle() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 10));
        set.add(range(90, 100));
        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[90; 100)", toString(set));

        set.remove(range(50, 70));
        assertAll(
                () -> assertEquals(20, set.intsSize()),
                () -> assertEquals("[0; 10)[90; 100)", toString(set))
        );
    }

    @Test
    void testRemoveFromEmptyMiddleStrict() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 10));
        set.add(range(90, 100));
        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[90; 100)", toString(set));

        set.remove(range(10, 90));
        assertAll(
                () -> assertEquals(20, set.intsSize()),
                () -> assertEquals("[0; 10)[90; 100)", toString(set))
        );
    }

    @Test
    void testRemoveEmptyMiddleAndOverlapLeft() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 10));
        set.add(range(90, 100));
        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[90; 100)", toString(set));

        set.remove(range(5, 90));
        assertAll(
                () -> assertEquals(15, set.intsSize()),
                () -> assertEquals("[0; 5)[90; 100)", toString(set))
        );
    }

    @Test
    void testRemoveEmptyMiddleAndOverlapRight() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 10));
        set.add(range(90, 100));
        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[90; 100)", toString(set));

        set.remove(range(10, 93));
        assertAll(
                () -> assertEquals(17, set.intsSize()),
                () -> assertEquals("[0; 10)[93; 100)", toString(set))
        );
    }

    @Test
    void testRemoveEmptyMiddleAndOverlapBoth() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 10));
        set.add(range(90, 100));
        assertEquals(20, set.intsSize());
        assertEquals("[0; 10)[90; 100)", toString(set));

        set.remove(range(5, 93));
        assertAll(
                () -> assertEquals(12, set.intsSize()),
                () -> assertEquals("[0; 5)[93; 100)", toString(set))
        );
    }

    @Test
    void testRemoveFromMiddle() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 100));
        assertEquals(100, set.intsSize());
        assertEquals("[0; 100)", toString(set));

        set.remove(range(5, 93));
        assertAll(
                () -> assertEquals(12, set.intsSize()),
                () -> assertEquals("[0; 5)[93; 100)", toString(set))
        );
    }

    @Test
    void testRemoveFromMiddle100() {
        IntRangeSet set = new IntRangeSet();

        IntStream.range(0, 100)
                .mapToObj(i -> range(i * 10, (i + 1) * 10))
                .forEach(set::add);

        String expectedString = IntStream.range(0, 100)
                .mapToObj(i -> range(i * 10, (i + 1) * 10))
                .map(IntRange::toString)
                .collect(Collectors.joining());

        assertEquals(1000, set.intsSize());
        assertEquals(expectedString, toString(set));

        set.remove(range(5, 995));
        assertAll(
                () -> assertEquals(10, set.intsSize()),
                () -> assertEquals("[0; 5)[995; 1000)", toString(set))
        );
    }

    @Test
    void testRemoveFromMiddle100Sparce() {
        IntRangeSet set = new IntRangeSet();

        IntStream.range(0, 100)
                .mapToObj(i -> range(i * 10, i * 10 + 4))
                .forEach(set::add);

        String expectedString = IntStream.range(0, 100)
                .mapToObj(i -> range(i * 10, i * 10 + 4))
                .map(IntRange::toString)
                .collect(Collectors.joining());

        assertEquals(400, set.intsSize());
        assertEquals(expectedString, toString(set));

        set.remove(range(2, 993));
        assertAll(
                () -> assertEquals(3, set.intsSize()),
                () -> assertEquals("[0; 2)[993; 994)", toString(set))
        );
    }

    @Test
    void testAddRemoveLargeRanges() {
        IntRangeSet set = new IntRangeSet();

        set.add(range(0, 3_000_000));
        set.add(range(10_000_000, 20_000_000));
        set.add(range(5_000_000, 25_000_000));
        set.add(range(15_000_000, 20_000_000));

        assertEquals(23_000_000, set.intsSize());
        assertEquals("[0; 3000000)[5000000; 10000000)[10000000; 20000000)[20000000; 25000000)", toString(set));

        set.remove(range(16_000_000, 19_000_000));
        set.remove(range(2_000_000, 6_000_000));

        assertAll(
                () -> assertEquals(18_000_000, set.intsSize()),
                () -> assertEquals("[0; 2000000)[6000000; 10000000)[10000000; 16000000)[19000000; 20000000)[20000000; 25000000)", toString(set))
        );
    }

    @Test
    void testRange() {
        IntRangeSet set = new IntRangeSet();

        assertTrue(set.add(range(-5, 5)));
        assertEquals(10, set.intsSize());
        assertEquals("[-5; 5)", toString(set));
        assertEquals(List.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4), toIntList(set));

        assertTrue(set.add(range(9, 10)));
        assertEquals(11, set.intsSize());
        assertEquals("[-5; 5)[9; 10)", toString(set));
        assertEquals(List.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 9), toIntList(set));

        assertTrue(set.add(range(-100, -97)));
        assertEquals(14, set.intsSize());
        assertEquals("[-100; -97)[-5; 5)[9; 10)", toString(set));
        assertEquals(List.of(-100, -99, -98, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 9), toIntList(set));

        assertFalse(set.add(range(0, 3)));
        assertEquals(14, set.intsSize());
        assertEquals("[-100; -97)[-5; 5)[9; 10)", toString(set));
        assertEquals(List.of(-100, -99, -98, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 9), toIntList(set));

        assertFalse(set.add(range(2, 5)));
        assertEquals(14, set.intsSize());
        assertEquals("[-100; -97)[-5; 5)[9; 10)", toString(set));
        assertEquals(List.of(-100, -99, -98, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 9), toIntList(set));

        assertTrue(set.add(range(2, 7)));
        assertEquals(16, set.intsSize());
        assertEquals("[-100; -97)[-5; 5)[5; 7)[9; 10)", toString(set));
        assertEquals(List.of(-100, -99, -98, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 9), toIntList(set));

        assertFalse(set.remove(range(20, 25)));
        assertEquals(16, set.intsSize());
        assertEquals("[-100; -97)[-5; 5)[5; 7)[9; 10)", toString(set));
        assertEquals(List.of(-100, -99, -98, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 9), toIntList(set));

        assertTrue(set.remove(range(0, 3)));
        assertEquals(13, set.intsSize());
        assertEquals(List.of(-100, -99, -98, -5, -4, -3, -2, -1, 3, 4, 5, 6, 9), toIntList(set));

        assertFalse(set.remove(range(0, 3)));
        assertEquals(13, set.intsSize());
        assertEquals("[-100; -97)[-5; 0)[3; 5)[5; 7)[9; 10)", toString(set));
        assertEquals(List.of(-100, -99, -98, -5, -4, -3, -2, -1, 3, 4, 5, 6, 9), toIntList(set));

        assertTrue(set.remove(range(5, 10)));
        assertEquals(10, set.intsSize());
        assertEquals("[-100; -97)[-5; 0)[3; 5)", toString(set));
        assertEquals(List.of(-100, -99, -98, -5, -4, -3, -2, -1, 3, 4), toIntList(set));

        assertTrue(set.remove(range(-99, 99)));
        assertEquals(1, set.intsSize());
        assertEquals("[-100; -99)", toString(set));
        assertEquals(List.of(-100), toIntList(set));

        assertTrue(set.remove(range(-100, 100)));
        assertEquals(0, set.intsSize());
        assertEquals("", toString(set));
        assertEquals(List.of(), toIntList(set));
    }

    private static List<IntRange> toList(final IntRangeSet set) {
        return set.stream().collect(Collectors.toList());
    }

    private static String toString(final IntRangeSet set) {
        return set.stream()
                .map(IntRange::toString)
                .collect(Collectors.joining());
    }

    private static List<Integer> toIntList(final IntRangeSet set) {
        Iterator<Integer> it = set.intsIterator();
        return Stream.generate(it::next)
                .limit(set.intsSize())
                .collect(Collectors.toList());
    }
}