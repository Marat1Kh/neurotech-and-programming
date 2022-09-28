package com.epam.rd.autotasks.pizzasplit;
import java.lang.*;
import java.util.Scanner;
import java.io.*;
    public class PizzaSplit {
    public static void main(String[] args) {
        int people, piece, pieces, pizza;
        Scanner scanner = new Scanner(System.in);
        people=scanner.nextInt();piece=scanner.nextInt();
        pieces=piece;
        while(pieces%people!=0 || pieces < people)
        {
            pieces+=piece;
        }
        pizza=pieces/piece;
        System.out.println(pizza);
    }
}
