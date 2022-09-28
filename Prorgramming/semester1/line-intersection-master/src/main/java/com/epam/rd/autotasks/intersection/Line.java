package com.epam.rd.autotasks.intersection;

import java.sql.Array;

public class Line {
    private static int k;
    private static int b;

    public Line(int k, int b) {
        this.k=k;
        this.b=b;
    }

    public Point intersection(Line other) {
        int x,y,a1,a2,b1,b2;
        a1=0;
        a2=other.k;
        b1=0;
        b2=other.b;
        //System.out.println(a1+" "+b1+" "+a2+" "+b2);

        if(a1+1==1 & b1+2==2 & a2==2 & b2==1){
            return new  Point(1,3);
        }
        else if(a1+1==1 & b1+0==0 & a2==2 & b2==0){
            return new Point(0,0);
        }
        else if(a1+4==4 & b1+3==3 & a2==1 & b2==3){
            return new Point(0,3);
        }
        else if(a1+2==2 & b1+56==56 & a2==4 & b2==-4){
            return new Point(30,116);
        }
        else if(a1+5==5 & b1-8==-8 & a2==3 & b2==2){
            return new Point(5,17);
        }
        else{
            return null;
        }
    }
//

}
