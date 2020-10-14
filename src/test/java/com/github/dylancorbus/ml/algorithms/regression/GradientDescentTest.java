package com.github.dylancorbus.ml.algorithms.regression;

import com.github.dylancorbus.ml.algorithms.regression.impl.GradientDescentImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class GradientDescentTest {

    double[][] matrix1;
    @Before
    public void setup() {
        double[][] matrix1 = new double[4][2];
        double[] a = new double[]{6.1101,7,17.592};
        double[] b = new double[]{5.5277,2,9.1302};
        double[] c = new double[]{8.5186,5,13.662};
        double[] d = new double[]{7.0032,4,11.854};
        matrix1[0] = a;
        matrix1[1] = b;
        matrix1[2] = c;
        matrix1[3] = d;
        System.out.println("population|profit");
        for (int i = 0; i < matrix1.length; i++) {
            for (int i1 = 0; i1 < matrix1[i].length; i1++) {
                System.out.print("  " + matrix1[i][i1]);
            }
            System.out.print("\n");
        }
    }

    @Test
    public void testModel() {
        GradientDescentImpl gd = new GradientDescentImpl(0.001);
        gd.fit(matrix1);
        System.out.println(Arrays.toString(gd.predict(new double[][]{new double[]{5.8598, 1}})));
    }
}
