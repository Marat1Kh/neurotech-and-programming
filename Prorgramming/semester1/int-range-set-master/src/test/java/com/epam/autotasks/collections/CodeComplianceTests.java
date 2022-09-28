package com.epam.autotasks.collections;

import org.junit.jupiter.api.Test;

import java.util.AbstractSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CodeComplianceTests {

    @Test
    void testAncestor() {
        assertEquals(AbstractSet.class, IntRangeSet.class.getSuperclass());
    }

    @Test
    void testIntRangeIsNotComparable() {
        assertFalse(Comparable.class.isAssignableFrom(IntRange.class));
        assertEquals(Object.class, IntRange.class.getSuperclass());
    }
}