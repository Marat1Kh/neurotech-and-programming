package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = new int[]{18, 1, 3, 6, 7, -5};

        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }
    public static int[] removeLocalMaxima(int[] array){

        try {
            int a = 0;
            int count=0;
            for (int i = 0; i<array.length; i++) {
                if(i!=(array.length-1) && i!=0)
                    if (array[i] > array[i+1] && (array[i] > array[i-1])) {
                    }else{
                        count++;
                    }
                else
                if(i==(array.length-1)){
                    if(array[i]>array[i-1]) {
                    }else {
                        count++;
                    }
                }else{
                    if(i==0){
                        if(array[i]>array[i+1]){
                        }else{
                            count++;
                        }
                    }
                }
            }
            int[] check = new int[count];
            for (int i = 0; i<array.length; i++) {
                if(i!=(array.length-1) && i!=0)
                    if (array[i] > array[i+1] && (array[i] > array[i-1])) {
                    }else{
                        check[a] = array[i];
                        a++;
                    }
                else
                if(i==(array.length-1)){
                    if(array[i]>array[i-1]) {
                    }else {
                        check[a] = array[i];
                    }
                }else{
                    if(i==0){
                        if(array[i]>array[i+1]){
                        }else{
                            check[a] = array[i];
                            a++;
                        }
                    }
                }
            }
            return check;
        } catch (UnsupportedOperationException err) {
            throw new UnsupportedOperationException();
        }
    }
}