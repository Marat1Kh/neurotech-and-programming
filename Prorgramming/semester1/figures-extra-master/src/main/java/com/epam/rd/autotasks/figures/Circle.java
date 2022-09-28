package com.epam.rd.autotasks.figures;

class Circle extends Figure{
    public Point a;
    public double r;

    public Circle(Point a, double r) {
        if ( a == null | r <= 0 ) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.r = r;
    }

    public Point centroid() {return a;}

    public boolean isTheSame(Figure figure) {
        if (figure instanceof Circle) {
            System.out.println(((Circle) figure).r);
            System.out.println(this.r);
            if (compareFloats(this.r, ((Circle) figure).r) & compareFloats(this.a.getX(), ((Circle) figure).a.getX()) & compareFloats(this.a.getY(), ((Circle) figure).a.getY())) {
                return true;
            }
        }
        return false;
    }
    public static boolean compareFloats(double f1, double f2)
    {
        if (Math.abs(f1 - f2) < 0.001f)
        {
            return true;
        }
        return false;
    }
}
