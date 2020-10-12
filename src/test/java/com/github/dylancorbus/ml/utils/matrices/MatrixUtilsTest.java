package com.github.dylancorbus.ml.utils.matrices;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MatrixUtilsTest {

    int[][] matrix1;
    int[][] matrix2;
    int[] vector2;

    @Before
    public void setup() {
        matrix1 = new int[3][4];
        int[] a = new int[]{0, 1, 2, 3};
        int[] b = new int[]{2, 3, 8, 5};
        int[] c = new int[]{1, 2, 3, 4};
        matrix1[0] = a;
        matrix1[1] = b;
        matrix1[2] = c;
        for (int i = 0; i < matrix1.length; i++) {
            for (int i1 = 0; i1 < matrix1[i].length; i1++) {
                System.out.print(matrix1[i][i1] + " ");
            }
            System.out.print("\n");
        }
        matrix2 = new int[3][2];
        int[] one = new int[]{5, 10};
        int[] two = new int[]{10, 20};
        int[] three = new int[]{20, 40};
        matrix2[0] = one;
        matrix2[1] = two;
        matrix2[2] = three;
        vector2 = new int[2];
        vector2[0] = 1;
        vector2[1] = 2;
    }

    @Test
    public void linearRegression() {
        double[] expected = new double[]{25, 50, 100};
        double[] actual = MatrixUtils.linearRegression(matrix2, vector2);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));

    }

    @Test
    public void getColVector() {
        double[] expected = new double[]{2, 8, 3};
        double[] actual = MatrixUtils.getColVector(matrix1, 3);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));
    }

    @Test
    public void getRowVector() {
        double[] expected = new double[]{2, 3, 8, 5};
        double[] actual = MatrixUtils.getRowVector(matrix1, 2);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));
    }
}
