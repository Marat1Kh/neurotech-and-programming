package com.epam.rd.autotasks.meetanagent;
import java.util.Scanner;
public class MeetAnAgent {
    final static String password = "Swordfish"; //You can change pass, but don't change the type

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String word=in.nextLine();
        if(word.equals(password)){
            System.out.println("Hello, Agent");
        }
        else{
            System.out.println("Access denied");
        }
    }
}