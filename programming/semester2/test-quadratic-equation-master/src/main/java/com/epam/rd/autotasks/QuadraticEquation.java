package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        String s = new String();
        if (a == 0) throw new IllegalArgumentException();
        double d = b*b - 4 * a * c;
        if (d > 0) s = (((-b - Math.sqrt(d)) / (2 * a)) + " " + ((-b + Math.sqrt(d)) / (2 * a)));
        if (d == 0) s = (-b / (2 * a)) + "";
        if (d < 0) s = "no roots";
        return s;
    }

}