package com.epam.autotasks.collections;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.TreeSet;

class RangedOpsIntegerSet extends AbstractSet<Integer> {
    AbstractSet<Integer> st = new TreeSet<>(); //create a set we will refer to, TreeSet is good for sorting numbers in natural order;
    public boolean add(int fromInclusive, int toExclusive) {
        boolean f = false; //f checks if the number is in the set
        for (int i = fromInclusive; i < toExclusive; i++)
        {
            if (!st.contains(i)) //if the number is in the set, we don't need to add it
            {
                st.add(i);
                f  = true; // the number was successfully added
            }
        }
        return f;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
        boolean f = false;
        for (int i = fromInclusive; i < toExclusive; i++)
        {
            if (st.contains(i)) //if the number is not in the set, we don't remove anything
            {
                st.remove(i);
                f = true; // the number was successfully removed
            }
        }
        return f;
    }

    @Override
    public boolean add(final Integer integer) {
        return st.add(integer);
    }

    @Override
    public boolean remove(final Object o) {
        return st.remove(o);
    }

    @Override
    public Iterator<Integer> iterator() {
        return st.iterator();
    }

    @Override
    public int size() {
        return st.size();
    }
}
