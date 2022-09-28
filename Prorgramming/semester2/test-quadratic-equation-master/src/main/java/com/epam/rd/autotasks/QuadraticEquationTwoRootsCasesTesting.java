package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;
    private String[] temp;
    private String expectedResultX1;
    private String expectedResultX2;

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expectedResultX1 = expected;
    }

    @Test
    public void testTwoRootsCase() {
        String solveAns = quadraticEquation.solve(a, b, c);
        String[] split = solveAns.split(" ");
        String[] split1 = expectedResultX1.split(" ");
        if (split.length < 2) fail();
        else {
            try {
                double x1 = Double.valueOf(split[0]);
                double x2 = Double.valueOf(split[1]);
                double Answexpected_x1 = Double.valueOf(split1[0]);
                double Answexpected_x2 = Double.valueOf(split1[1]);
                if ((Math.abs(x1 - Answexpected_x1) < 0.001 || Math.abs(x1 - Answexpected_x2) < 0.001)
                        && (Math.abs(x2 - Answexpected_x1) < 0.001 || Math.abs(x2 - Answexpected_x2) < 0.001))
                    assertTrue(1 == 1);
                else assertTrue(1 == 0);
            } catch (Exception e) {
                fail();
            }
        }
    }
    @Parameterized.Parameters()
    public static Collection testNumbersForTwoRootsCases() {
        return Arrays.asList(new Object[][]{{1, 3, -4, "-4.0 1.0"}, {1, -1, -2, "-1.0 2.0"},
                {1, -3, -10, "-2.0 5.0"}, {1, -7, 12, "3.0 4.0"}});
    }
}