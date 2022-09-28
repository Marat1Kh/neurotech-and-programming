package com.epam.rd.autotasks.intersection;

public class Point {
    public static int y;
    public static int x;



    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d;%d)", x, y);
    }
}
