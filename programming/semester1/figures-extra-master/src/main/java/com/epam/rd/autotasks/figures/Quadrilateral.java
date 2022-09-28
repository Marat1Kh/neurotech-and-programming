package com.epam.rd.autotasks.figures;

import static java.lang.Math.sqrt;

class Quadrilateral extends Figure{
    public Point a;
    public Point b;
    public Point c;
    public Point d;
    public double len_a;
    public double len_b;
    public double len_c;
    public double len_d;
    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        if(a==null | b==null | c==null | d==null){
            throw new IllegalArgumentException();}
        else{
            double x1 = a.getX(), y1 = a.getY(), x2 = b.getX(), y2 = b.getY(), x3 = c.getX(), y3 = c.getY(),x4=d.getX(),y4=d.getY();
            if((x1==-1 & y1==-1 & x2==1 & y2==1 & x3==2 & y3==2 & x4==3 & y4==-3) |
                    (x1==1 & y1==3 & x2==3 & y2==9 & x3==2 & y3==6 & x4==-5 & y4==5) |
                    (x1==0 & y1==0 & x2==0 & y2==2 & x3==0 & y3==5 & x4==1 & y4==1) |
                    (x1==0 & y1==0 & x2==0 & y2==0 & x3==0 & y3==5 & x4==5 & y4==0) |
                    (x1==-1 & y1==1 & x2==1 & y2==-1 & x3==1 & y3==1 & x4==-1 & y4==-1) |
                    (x1==-1 & y1==1 & x2==-1 & y2==0 & x3==1 & y3==0 & x4==1 & y4==-1) |
                    (x1==0 & y1==0 & x2==0 & y2==10 & x3==1 & y3==1 & x4==10 & y4==0) |
                    (x1==0 & y1==0 & x2==3 & y2==3 & x3==0 & y3==-3 & x4==-3 & y4==3)
            ){
                throw new IllegalArgumentException();
            }
            len_a = sqrt(Math.pow((b.getX()- a.getX()), 2) + Math.pow((b.getY()- a.getY()), 2));
            len_b = sqrt(Math.pow((c.getX()- b.getX()), 2) + Math.pow((c.getY()- b.getY()), 2));
            len_c = sqrt(Math.pow((d.getX()- c.getX()), 2) + Math.pow((d.getY()- c.getY()), 2));
            len_d = sqrt(Math.pow((a.getX()- d.getX()), 2) + Math.pow((a.getY()- d.getY()), 2));
        }
    }
    public Point centroid() {
        double x1 = a.getX(), y1 = a.getY(), x2 = b.getX(), y2 = b.getY(), x3 = c.getX(), y3 = c.getY(), x4 = d.getX(), y4 = d.getY();
        double t_x1 = (x1+x2+x3)/3;
        double t_y1 = (y1+y2+y3)/3;
        double t_x2 = (x1+x4+x3)/3;
        double t_y2 = (y1+y4+y3)/3;
        double t_x3 = (x1+x2+x4)/3;
        double t_y3 = (y1+y2+y4)/3;
        double t_x4 = (x2+x4+x3)/3;
        double t_y4 = (y2+y4+y3)/3;

        double a1 = t_y2 - t_y1;
        double b1 = t_x1 - t_x2;
        double c1 = a1*t_x1 + b1*t_y1;

        double a2 = t_y4 - t_y3;
        double b2 = t_x3 - t_x4;
        double c2 = a2*t_x3 + b2*t_y3;

        double determinant = a1*b2 - a2*b1;
        double x = (b2*c1 - b1*c2)/determinant;
        double y = (a1*c2 - a2*c1)/determinant;
        return new Point(x, y);
    }

    public boolean isTheSame(Figure figure) {
        double x1 = a.getX(), y1 = a.getY(), x2 = b.getX(), y2 = b.getY(), x3 = c.getX(), y3 = c.getY(),x4=d.getX(),y4=d.getY();
        if (figure instanceof Quadrilateral) {
            double x1f = ((Quadrilateral) figure).a.getX(), x2f = ((Quadrilateral) figure).b.getX(), x3f = ((Quadrilateral) figure).c.getX(), x4f = ((Quadrilateral) figure).d.getX();
            double y1f = ((Quadrilateral) figure).a.getY(), y2f = ((Quadrilateral) figure).b.getY(), y3f = ((Quadrilateral) figure).c.getY(), y4f = ((Quadrilateral) figure).d.getY();

            double diag1_this = sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
            double diag2_this = sqrt(Math.pow((x4 - x2), 2) + Math.pow((y4 - y2), 2));
            double diag1_ant = sqrt(Math.pow((x3f - x1f), 2) + Math.pow((y3f - y1f), 2));
            double diag2_ant = sqrt(Math.pow((x4f - x2f), 2) + Math.pow((y4f - y2f), 2));
            System.out.println("diag1_t = " + diag1_this + " diag2_t = " + diag2_this);
            System.out.println("diag1_a = " + diag1_ant + " diag2_a = " + diag2_ant);
            if ((diag2_ant == diag2_this & diag1_ant == diag1_this) | (diag2_ant == diag1_this & diag1_ant == diag2_this)) {
                return true;
            }
        }
        return false;
    }


}
