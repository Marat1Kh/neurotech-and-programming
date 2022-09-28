package com.epam.autotasks.collections;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

class MedianQueue<E extends Comparable<E>> extends AbstractQueue<E> {
    Queue <E> qu = new LinkedBlockingDeque<>(); //create a queue
    @Override
    public Iterator<E> iterator() {
        return qu.iterator();
    }

    @Override
    public int size() {
        return qu.size();
    }

    @Override
    public boolean offer(final E e) {
        return qu.add(e);
    }

    @Override
    public E poll() {
        return qu.poll();
    }

    @Override
    public E peek() {
        int size = qu.size();
        AbstractQueue<E> sortirovka = new PriorityQueue<>(); //we create a queue which puts the elements in natural order
        sortirovka.addAll(qu); //add the original queue
        Deque<E> help = new LinkedBlockingDeque<>(); //just a queue which we will use for solving this awful problem
        //attention! help is a DEQUE. it means that we can add elements to the beginning and to the end, also we can read it from the both sides of the queue
        AbstractQueue<E> rezult = new LinkedBlockingQueue<>(); //the result (the queue which was sorted according to the task)
        int end; //a number of "the median-element"
        if (size % 2 == 0)
        {
            end = size / 2 - 1;
        }
        else
        {
            end = size / 2;
        }
        for (int i = 0; i < end; i++)
        {
            help.addFirst(sortirovka.poll()); //we put elements before the median to the "help-queue" and delete them from the sorted queue
        }
        //attention! we put elements to the BEGINNING, not to the end (elements are added to the end by default, but NOT NOW)
        //after all this shit the first element in "sortirovka" is the median (we were obliged to find it)
        //that's the reason why we mustn't change "sortirovka" anymore
        AbstractQueue<E> dubl = new PriorityQueue<>(); //we clone "sortirovka" as we will need values of it's elements
        dubl.addAll(sortirovka);
        if (size % 2 == 1)
        {
            int helps = help.size();
            for (int i = 0; i < helps; i++)//we put elements in order we need in the task
            //look at the tests attentively. I hope you'll understand what order is required. You're not stupid)
            {
                rezult.add(dubl.poll());
                rezult.add(help.pollFirst()); //add elements from the beginning of deque and delete it from "help"
            }
            rezult.add(dubl.poll());
            qu.clear(); //delete everything from the original queue
            qu.addAll(rezult); //put the result of my sorrows to the main queue
        }
        else
        {
            int helps = help.size(); //in this part of the code we do the same, but we have with even amount of elements
            rezult.add(dubl.poll());// if you're really interested in the order of elements (sincerely, I doubt),
            // you should look at tests again
            for (int i = 0; i < helps; i++)
            {
                rezult.add(dubl.poll());
                rezult.add(help.pollFirst());
            }
            rezult.add(dubl.poll());
            qu.clear();
            qu.addAll(rezult);
        }
        return sortirovka.peek();//return the first element in "sortirovka". it's the median, 'cause we've deleted everything before it
    }
}
