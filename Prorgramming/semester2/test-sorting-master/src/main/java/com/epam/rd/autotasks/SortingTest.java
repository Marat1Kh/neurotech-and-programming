package com.epam.rd.autotasks;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class SortingTest {

    Sorting sorting = new Sorting();

    @Test(expected = UnsupportedOperationException.class)
    public void testNullCase(){
    int[] expected = null;
    int[] arr = null;
    sorting.sort(arr);
    assertArrayEquals(expected, arr);
    }

    @Test
    public void testEmptyCase(){
    int[] expected = new int[0];
    int[] arr = new int[0];
    sorting.sort(arr);
    assertArrayEquals(expected, arr);
    }

    @Test
    public void testSingleElementArrayCase() {
    int[] expected = {1};
    int[] arr = {1};
    sorting.sort(arr);
            assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortedArraysCase() {
        int[] expected = {5};
        int[] arr = {5};
        sorting.sort(arr);
        assertArrayEquals(expected, arr);

    }

    @Test
    public void testOtherCases() {
    int[] expected = {1,2,3,4,5};
    int[] arr = {5,4,3,2,1};
    sorting.sort(arr);
    assertArrayEquals(expected, arr);
    }

}