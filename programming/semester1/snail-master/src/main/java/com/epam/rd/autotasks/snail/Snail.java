package com.epam.rd.autotasks.snail;
import java.util.Scanner;
public class Snail
{
    public static void main(String[] args)
    {
      int up, down, height, day, sec;
      Scanner input = new Scanner(System.in);
      day =1; sec=0;
      up = input.nextInt();
      down = input.nextInt();
      height = input.nextInt();
      if(up>=height){
          System.out.println(1);

      }
      else if(up-down<=0){
          System.out.println("Impossible");
      }
      else
          while(height>sec){
              sec= sec+up;
              if(sec>=height){
                  System.out.println(day);
              break;}
          sec=sec-down;
          day++;}
    }
}
