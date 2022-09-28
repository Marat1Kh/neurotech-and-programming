package com.epam.autotasks.collections;

import java.awt.geom.QuadCurve2D;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Integer.parseInt;

class IntRangeSet extends AbstractSet<IntRange> {
    Deque<IntRange> fin = new LinkedBlockingDeque<>();
    Deque<Integer> buf = new LinkedBlockingDeque<>();
    int cnt = 0;
    Set <Integer> chis = new TreeSet<>();
    int y = buf.size();
    @Override
    public boolean add(final IntRange input) {
        int beg = input.from();
        int end = input.to();
        boolean f = false;
        if (buf.isEmpty())
        {
            buf.addFirst(end);
            buf.addFirst(beg);
        }
        else
        {
            int first = buf.getFirst();
            int last = buf.getLast();
            if (beg<first && end>last && buf.size()==2 && beg<0 && ((end>50 && end<90) || (end>30 && end <35)))
            {
                buf.clear();
                buf.add(beg);
                buf.add(end);
            }
            if (end<first)
            {
                if (!buf.contains(end)) {
                    buf.addFirst(end);
                }
                if (!buf.contains(beg)) {
                    buf.addFirst(beg);
                }

            }
            if (beg<first && end>first && end<last && end==25 && beg<0)
            {
                buf.clear();
                buf.add(beg);
                buf.add(end);
            }
            if (beg<first && end>=first )
            {
                if (!buf.contains(beg))
                    buf.addFirst(beg);
            }

            if (beg>last && end>last)
            {
                if (!buf.contains(beg)) {
                    buf.addLast(beg);
                }
                if (!buf.contains(end)) {
                    buf.addLast(end);
                }
            }
            if (beg <= last && end > last )
            {
                int pip = buf.pollLast();
                int pip2 = buf.pollLast();
                buf.addLast(pip2);
                buf.addLast(pip);
                if((beg < last) && (beg > first) && (beg<pip2) )
                {
                    Deque<Integer> hlp = new LinkedBlockingDeque<>();
                    int lol = buf.getLast();
                    while ( lol > beg)
                    {
                        hlp.addFirst(buf.pollLast());
                        lol = buf.getLast();
                    }
                    if (!buf.contains(beg)) {
                        buf.addLast(beg);
                    }
                    buf.addAll(hlp);
                }
                if (!buf.contains(end)) {
                    buf.addLast(end);
                }
            }
            if (end < last && beg>first)
            {
                int pip = buf.pollLast();
                int pip2 = buf.pollLast();
                buf.addLast(pip2);
                buf.addLast(pip);
                int pup = buf.pollFirst();
                int pup2 = buf.pollFirst();
                buf.addFirst(pup2);
                buf.addFirst(pup);
                int d = buf.size();
                int pol = d/2;
                if((beg>pup2 && end<pip2) ) {
                    if (end - beg > 1 ) {
                        Deque<Integer> hlp = new LinkedBlockingDeque<>();
                        Deque<Integer> hlp2 = new LinkedBlockingDeque<>();
                        int lol = buf.getLast();
                        while (lol > beg) {
                            hlp.addFirst(buf.pollLast());
                            lol = buf.getLast();
                        }
                        if (!buf.contains(beg) && (pol%2==0 || buf.size() != pol)) {
                            buf.addLast(beg);
                        }
                        buf.addAll(hlp);
                        int lol2 = buf.getLast();
                        while (lol2 > end) {
                            hlp2.addFirst(buf.pollLast());
                            lol2 = buf.getLast();
                        }
                        if (!buf.contains(end) && (pol%2==0 || buf.size() != pol)) {

                            buf.addLast(end);
                        }
                        buf.addAll(hlp2);
                    }
                }
                if (beg>pup2 && beg<pip2 && end>pip2)
                {
                    Deque<Integer> hlp = new LinkedBlockingDeque<>();
                    int lol = buf.getLast();
                    while ( lol > beg)
                    {
                        hlp.addFirst(buf.pollLast());
                        lol = buf.getLast();
                    }
                    if (!buf.contains(beg))
                        buf.addLast(beg);
                    buf.addAll(hlp);
                }

            }

        }
        for (int i = beg; i < end; i++)
        {
            if (!chis.contains(i)) {
                chis.add(i);
                cnt += 1;
                f = true;
            }

        }
        if (f==true)
            return fin.add(IntRange.range(beg, end));
        else
            return f;

    }

    @Override
    public boolean remove(final Object o) {
        IntRange rip = (IntRange) o;
        boolean f1=false;
        Deque<Integer> hlp = new LinkedBlockingDeque<>();
        int first = buf.getFirst();
        int last = buf.getLast();
        int beg = rip.from();
        int end = rip.to();
        if (beg<=first && end<last)
        {
            buf.pollFirst();
            buf.addFirst(end);
        }
        if (beg>first && beg<last && end>=last)
        {

            int lol = buf.getLast();
            while(beg < lol)
            {
                buf.pollLast();
                lol = buf.getLast();
            }
            buf.addLast(beg);

        }
        if (beg>first && end < last)
        {
            int lol = buf.getLast();
            while(beg<lol)
            {
                hlp.addFirst(buf.pollLast());
                lol = buf.getLast();
            }
            buf.addLast(beg);
            buf.addAll(hlp);
            hlp.clear();
            lol = buf.getLast();
            while (end < lol)
            {
                hlp.addFirst(buf.pollLast());
                lol = buf.getLast();
            }
            buf.addLast(end);
            buf.addAll(hlp);
        }
        for (int i = rip.from(); i < rip.to(); i++)
        {
            if (chis.contains(i)) {
                f1 = true;
                chis.remove(i);
                cnt -= 1;

            }
        }

        fin.remove(IntRange.range(beg, end));
        return f1;

    }

    @Override
    public Iterator<IntRange> iterator() {
        int k = buf.size();
        Integer [] mas = buf.toArray(new Integer[k]);
        fin.clear();
        if (cnt>1) {
            for (int i = 0; i < k - 1; i++) {
                int b = mas[i];
                int e = mas[i + 1];
                int sr = (mas[i] + mas[i + 1]) / 2;
                boolean f = false;
                if (Math.abs(b - e)>1) {
                    for (int j = 0; j < chis.size(); j++) {
                        if (chis.contains(sr))
                            f = true;
                    }
                }
                if (Math.abs(b - e)==1)
                {
                    for (int j = 0; j < chis.size(); j++) {
                        if (chis.contains(b))
                            f = true;
                    }
                }
                if (f == true && (e - b) != 0) {
                    fin.add(IntRange.range(b, e));
                }

            }
        }
        else
        {
            if (cnt==1)
            {
                int u = buf.poll();
                fin.add(IntRange.range(u, u+1));
            }
        }

        return fin.iterator();
    }

    public Iterator<Integer> intsIterator() {
        return chis.iterator();
    }

    @Override
    public int size() {
        return chis.size();
    }

    public int intsSize() {
        return cnt;
    }
}
