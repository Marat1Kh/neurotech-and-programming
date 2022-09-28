package com.epam.rd.autotasks.max;
import java.util.*;
public class MaxMethod {
    public static int max(int[] values) {

        int max;
        max = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }
}