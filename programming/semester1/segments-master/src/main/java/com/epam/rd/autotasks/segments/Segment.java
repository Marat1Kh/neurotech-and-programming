package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {

    private final Point start;
    private final Point end;

    public static boolean isBetween(double a, double b, double c) {
        return b >= a ? c >= a && c <= b : c >= b && c <= a;
    }

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;

        if((start.getX() == end.getX() && start.getY() == end.getY()) || start == null || end == null){
            throw new IllegalArgumentException();
        }
    }

    double length() {
        return sqrt(pow(end.getX() - start.getX(), 2) + pow(end.getY() - start.getY(), 2));
    }

    Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    Point intersection(Segment another) {
        double a1 = end.getY() - start.getY();
        double a2 = another.end.getY() - another.start.getY();

        double b1 = start.getX() - end.getX();
        double b2 = another.start.getX() - another.end.getX();

        double v = a1*b2 - a2*b1;

        if (v != 0) {
            double c1 = -start.getX() * end.getY() + start.getY() * end.getX();
            double c2 = -another.start.getX() * another.end.getY() + another.start.getY() * another.end.getX();

            double x = (b1*c2 - b2*c1)/v;
            double y = (a2*c1 - a1*c2)/v;

            if (a1 * b2 == b1 * a2 && a1 * c2 == a2 * c1 && b1 * c2 == c1 * b2) {
                return null;
            }

            if ((a1 * b2 - a2 * b1) == 0) {
                return null;
            }

            if (isBetween(start.getX(), end.getX(), x) && isBetween(start.getY(), end.getY(), y) && isBetween(another.start.getX(), another.end.getX(), x) && isBetween(another.start.getY(), another.end.getY(), y)){

                return new Point(x, y);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
