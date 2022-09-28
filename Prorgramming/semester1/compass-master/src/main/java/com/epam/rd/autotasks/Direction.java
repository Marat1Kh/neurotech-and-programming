package com.epam.rd.autotasks;

import java.util.Optional;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private int degrees;

    public int getDegrees() {
        return degrees;
    }

    public static Direction ofDegrees(int degrees) {

        degrees %= 360;
        degrees = negativeToPositive(degrees);
        for (Direction direction : values()){
            if (direction.degrees == degrees) { return direction; }
        }
        return null;
        // throw new UnsupportedOperationException();
    }

    public static Direction closestToDegrees(int degrees) {
        int min = Integer.MAX_VALUE;
        Direction minDir = null;
        degrees %= 360;
        degrees = negativeToPositive(degrees);
        for (Direction direction : values()){

            int dif = Math.abs(direction.degrees - degrees);
            if (dif < min ){
                min = dif;
                minDir = direction;

            }
        }
        return minDir;
        // throw new UnsupportedOperationException();
    }

    public Direction opposite() {
        return ofDegrees(degrees + 180);
        //throw new UnsupportedOperationException();
    }

    public int differenceDegreesTo(Direction direction) {
        int a = Math.abs(this.degrees - direction.getDegrees());
        return a > 180 ? 360 - a : a;
        //return  negativeToPositive(this.degrees - direction.getDegrees());
        //throw new UnsupportedOperationException();
    }
    private static int negativeToPositive(int degrees){
        if (degrees < 0){
            return 360 + degrees;
        }
        return degrees;
    }
}
