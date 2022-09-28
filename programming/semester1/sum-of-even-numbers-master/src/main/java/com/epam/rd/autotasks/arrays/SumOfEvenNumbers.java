package com.epam.rd.autotasks.arrays;
import java.util.Scanner;
public class SumOfEvenNumbers {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 8, 15, 199};

        System.out.println(sum(array));
    }

    public static int sum(int[] array){
        int sum;
        sum = 0;
        if(array==null || array.length==0){
            try{
                throw new UnsupportedOperationException("Array is empty");
            }
            catch (UnsupportedOperationException e){

            }
        }
        else for(int i=0; i<array.length; i++){
            if(array[i]%2==0){
                sum= sum + array[i];
            }
        }
        return sum;
    }
}
