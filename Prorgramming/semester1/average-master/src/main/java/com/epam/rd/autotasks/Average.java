package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        int number = input.nextInt();
        while (number != 0) {
            sum += number;
            count++;
            number = input.nextInt();
        }
        System.out.println(sum/count); }}