package com.epam.rd.autocode.iterator;
import java.lang.reflect.Array;
import java.util.*;

class Iterators {

    public static Iterator<Integer> intArrayXTimesIterator(int[] array, int times) {
         List<Integer> arroy =new ArrayList<Integer>();
        if(times == 0)
            throw new IllegalArgumentException();

        if(array.equals(null))
            throw new NoSuchElementException();
            for(int i=0; i<array.length; i++){
                arroy.addAll(Collections.nCopies(times, array[i]));

            }

        return arroy.iterator();
    }

    }