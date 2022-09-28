package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        int sec = seconds % 60;
        int min = (seconds / 60)%60;
        int hours = (seconds/60)/60;
        if (hours==24) {
            System.out.printf("0:%02d:%02d",min, sec);
        }
        else {
            System.out.printf("%01d:%02d:%02d%n", hours, min, sec);
        }
    }
}
