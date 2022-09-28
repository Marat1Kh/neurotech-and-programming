package com.epam.rd.autotasks;

class CycleSwap {
    static void cycleSwap(int[] array) {
        if(array.length == 0){
            return;
        }
        int cur = array[array.length-1];
        for(int i = array.length-1; i>0;i--){
            array[i]=array[i-1];

        }
        array[0]=cur;
    }

    static void cycleSwap(int[] array, int shift) {
        if(array.length<shift){
            return;
        }
        int temp= array.length-shift;
        int[] brr=new int[array.length];
        int index=0;
        for(int i=temp;i<array.length;i++){
            brr[index++]=array[i];

        }
        for (int i=0;i<temp;i++){
            brr[index++]=array[i];
        }
        for(int i=0;i< array.length;i++){
            array[i]=brr[i];
        }

    }
}