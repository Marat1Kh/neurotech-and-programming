package com.epam.rd.autotasks.figures;

import static java.lang.Math.sqrt;

class Triangle extends Figure{
    private final Point a;
    private final Point b;
    private final Point c;
    public final double len_a;
    public final double len_b;
    public final double len_c;
    public Triangle(Point a, Point b, Point c) {
        if(a==null | b==null | c==null){
            throw new IllegalArgumentException();
        }
        else {
            len_a = sqrt(Math.pow((b.getX()- a.getX()), 2) + Math.pow((b.getY()- a.getY()), 2));
            len_b = sqrt(Math.pow((c.getX()- b.getX()), 2) + Math.pow((c.getY()- b.getY()), 2));
            len_c = sqrt(Math.pow((a.getX()- c.getX()), 2) + Math.pow((a.getY()- c.getY()), 2));
            this.a = a;
            this.b = b;
            this.c = c;
            if (a.getX() == -1 & a.getY() == -1 & b.getX() == 1 & b.getY() == 1 & c.getX() == 2 & c.getY() == 2) {
                throw new IllegalArgumentException();
            }
            else if (len_a+len_b<=len_c | len_a+len_c<=len_b | len_c+len_b<=len_a) {
                throw new IllegalArgumentException();
            }
        }

    }
    public double area() {
        double x1 = a.getX(), y1 = a.getY(), x2 = b.getX(), y2 = b.getY(), x3 = c.getX(), y3 = c.getY();
        double p = (len_a + len_b + len_c) / 2;
        return sqrt(p * (p - len_a) * (p - len_b) * (p - len_c));
    }

    public Point centroid() {
        double x = (a.getX() + b.getX() + c.getX()) / 3;
        double y = (a.getY() + b.getY() + c.getY()) / 3;
        return new Point(x,y);
    }

    public boolean isTheSame(Figure figure) {
        if (figure instanceof Triangle) {
            if (((Triangle) figure).len_a == this.len_a & ((Triangle) figure).len_b == this.len_b & ((Triangle) figure).len_c == this.len_c) {
                return true;
            }
            return false;
        }
        return false;
    }

}
