package com.epam.rd.autocode.decorator;
import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
public class miniDec implements List<String> {
    public List<String> nextList;

     miniDec(List<String> list) {
        this.nextList = list;
    }

    public class EvenIndexIterator implements Iterator {

        private  List<String> sourceList;
        private int x;
        private  int size;

        public EvenIndexIterator(List<String> sourceList) {
            this.sourceList = sourceList;
            this.x = 0;
            this.size = sourceList.size();
        }

        @Override
        public boolean hasNext() {
            return x*2 < size;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String z = sourceList.get(x*2);
            ++x;
            return z;
        }
    }

    public class EvenIndexIteratorL implements ListIterator {

        private final List<String> sourceList;
        private int x;
        private final int size;

        public EvenIndexIteratorL(List<String> sourceList) {
            this.sourceList = sourceList;
            this.x = 0;
            this.size = sourceList.size();
        }

        @Override
        public boolean hasNext() {
            return x*2 < size;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String z = sourceList.get(x*2);
            ++x;
            return z;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }

    @Override
    public void replaceAll(UnaryOperator<String> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public int size() {
        int size = nextList.size();
        return (size + 1) / 2;
    }

    @Override
    public String get(int ind) {
        int size = nextList.size();
        if (ind*2 < size) {
            return nextList.get(ind*2);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String set(int ind, String sourceList) {
        return null;
    }

    @Override
    public void add(int ind, String sourceList) {

    }

    @Override
    public String remove(int ind) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public void sort(Comparator<? super String> c) {
        List.super.sort(c);
    }

    @Override
    public Spliterator<String> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return List.super.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super String> filter) {
        return List.super.removeIf(filter);
    }

    @Override
    public Stream<String> stream() {
        return List.super.stream();
    }

    @Override
    public Stream<String> parallelStream() {
        return List.super.parallelStream();
    }
    @Override
    public Iterator<String> iterator(){
        return new EvenIndexIterator(nextList);
    }

    @Override
    public ListIterator<String> listIterator() {
        return new EvenIndexIteratorL(nextList);
    }

    @Override
    public ListIterator<String> listIterator(int ind) {
        return new EvenIndexIteratorL(nextList);
    }

    @Override
    public List<String> subList(int fromInd, int toInd) {
        return null;
    }
}

