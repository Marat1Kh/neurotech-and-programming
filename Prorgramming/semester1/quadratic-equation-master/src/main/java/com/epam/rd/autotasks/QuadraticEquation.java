package com.epam.rd.autotasks;

import java.util.Locale;
import java.util.Scanner;

public class QuadraticEquation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double D = b*b-4*a*c;

        if (D<0){
            System.out.print("no roots");
        } else {
            double x1 = (-b- Math.sqrt(D))/2/a;
            double x2 = (-b+ Math.sqrt(D))/2/a;
            System.out.print(x1==x2?""+x1:""+x1+" "+x2);
        }




    }


}