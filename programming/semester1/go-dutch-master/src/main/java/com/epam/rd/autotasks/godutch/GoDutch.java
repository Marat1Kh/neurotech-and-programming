package com.epam.rd.autotasks.godutch;
import java.util.Scanner;
public class GoDutch {

    public static void main(String[] args) {
    int dostar, aksha;
    Scanner input = new Scanner(System.in);
    aksha = input.nextInt();
    dostar = input.nextInt();
    if(aksha<0) {
        System.out.println("Bill total amount cannot be negative");
    }
    else if(dostar<=0){
        System.out.println("Number of friends cannot be negative or zero");
    }
    else {
        aksha= aksha +(aksha/100*10);
        System.out.print(aksha/dostar);
    }
    }
}
