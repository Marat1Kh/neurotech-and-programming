package com.epam.rd.autotasks.sequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {
        Scanner scan = new Scanner(System.in);
        int maximum = Integer.MIN_VALUE;
        int number = scan.nextInt();
        while (number != 0) {
            if (number > maximum) {
                maximum = number;
            }
            number = scan.nextInt();
        }
        return maximum;
    }

    public static void main(String[] args) {


        System.out.println(max());
    }
    }

