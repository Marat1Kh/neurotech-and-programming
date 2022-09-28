package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

public interface ThreadUnion extends ThreadFactory {
    int totalSize();
    int activeSize();
    void shutdown();
    boolean isShutdown();
    void awaitTermination();
    boolean isFinished();

    List<FinishedThreadResult> results();

    static ThreadUnion newInstance(String name){
       return new MyThreadUnion(name);
    }
}
    class MyThreadUnion implements ThreadUnion{
    String name;
    volatile int x =0;
    volatile List<Thread> wholeT = new ArrayList<>();
    volatile Map<Thread, Throwable> nth = new HashMap<>();
    volatile boolean charact = false;
    public MyThreadUnion(String name){
        this.name=name;
    }
    @Override
        public int totalSize(){
        return x;
    }

        @Override
        public int activeSize() {
            int actS = 0;
            for(int i=0; i<wholeT.size(); i++){
                if(wholeT.get(i).isAlive()) actS++;
            }
            return actS;
        }

        @Override
        public void shutdown() {
        for(int i=0; i<wholeT.size(); i++){
            wholeT.get(i).interrupt();
        }
        charact = true;
        }

        @Override
        public boolean isShutdown() {
            return charact;
        }

        @Override
        public void awaitTermination() {
        for(int i=0; i<wholeT.size(); i++){
            try{
                wholeT.get(i).join();
            }
            catch(InterruptedException e) {

            }
        }
        }

        @Override
        public boolean isFinished() {
            return charact && (this.activeSize() ==0);
        }

        @Override
        public List<FinishedThreadResult> results() {
            List<FinishedThreadResult> hariu = new ArrayList<>();
            for(int i=0; i<wholeT.size(); i++){
                if(!wholeT.get(i).isAlive()){
                    if(nth.containsKey(wholeT.get(i))) hariu.add(new FinishedThreadResult(wholeT.get(i).getName(), nth.get(wholeT.get(i))));
                else hariu.add(new FinishedThreadResult(wholeT.get(i).getName()));
                }
            }
            return hariu;
        }

        @Override
        public Thread newThread(Runnable r) {

        if(charact) throw new IllegalStateException();
        Thread too = new Thread(r, name + "-worker-" +(x++));
        too.setUncaughtExceptionHandler((t,e) -> nth.put(t,e));
        wholeT.add(too);
        return too;
    }
    }
