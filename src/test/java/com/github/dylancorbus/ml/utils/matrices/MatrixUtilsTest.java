package com.github.dylancorbus.ml.utils.matrices;

import com.github.dylancorbus.ml.exceptions.NonConformantArgumentException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MatrixUtilsTest {

    double[][] matrix1;
    double[][] matrix2;
    double[] vector2;

    @Before
    public void setup() {
        matrix1 = new double[3][4];
        double[] a = new double[]{0, 1, 2, 3};
        double[] b = new double[]{2, 3, 8, 5};
        double[] c = new double[]{1, 2, 3, 4};
        matrix1[0] = a;
        matrix1[1] = b;
        matrix1[2] = c;
        for (int i = 0; i < matrix1.length; i++) {
            for (int i1 = 0; i1 < matrix1[i].length; i1++) {
                System.out.print(matrix1[i][i1] + " ");
            }
            System.out.print("\n");
        }
        matrix2 = new double[3][2];
        double[] one = new double[]{5, 10};
        double[] two = new double[]{10, 20};
        double[] three = new double[]{20, 40};
        matrix2[0] = one;
        matrix2[1] = two;
        matrix2[2] = three;
        vector2 = new double[2];
        vector2[0] = 1;
        vector2[1] = 2;
    }

    @Test
    public void linearRegression() {
        double[] expected = new double[]{25, 50, 100};
        double[] actual = MatrixUtils.mult(matrix2, vector2);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));

    }

    @Test
    public void getColVector() {
        double[] expected = new double[]{2, 8, 3};
        double[] actual = MatrixUtils.getColVector(matrix1, 2);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));
    }

    @Test
    public void getRowVector() {
        double[] expected = new double[]{2, 3, 8, 5};
        double[] actual = MatrixUtils.getRowVector(matrix1, 1);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));
    }

    @Test
    public void vectorMultTest() {
        double[][] x = new double[][]{new double[]{1,2,3}};
        double[] theta = new double[]{5,6,7};
        double[] expected = new double[]{38};
        double[] actual = MatrixUtils.mult(x, theta);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));
        x = new double[][]{new double[]{1, 10}};
        theta = new double[]{5,6};
        expected = new double[]{65};
        actual = MatrixUtils.mult(x, theta);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));
        x = new double[][]{new double[]{1,2,3},new double[]{2,1,1}, new double[]{3,1,3}, new double[]{2,2,3}};
        theta = new double[]{2,2,1};
        expected = new double[]{9,7,11,11};
        actual = MatrixUtils.mult(x, theta);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected), Arrays.toString(actual)));
    }

    @Test
    public void getxTest() {
        double[][] matrix = new double[][]{new double[]{1,2,3},new double[]{2,1,1}, new double[]{3,1,3}};
        double[][] expected = new double[][]{new double[]{1,2},new double[]{2,1}, new double[]{3,1}};
        double[][] actual = MatrixUtils.getx(matrix);
        assertEquals(expected[0].length, actual[0].length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(Arrays.toString(expected[i]), Arrays.toString(actual[i]));
            System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected[i]), Arrays.toString(actual[i])));
        }
    }

    @Test
    public void getXTest() {
        double[][] matrix = new double[][]{new double[]{1,2,3,10},new double[]{1,2,3,10}, new double[]{1,2,3, 10}};
        double[][] expected = new double[][]{new double[]{1,1,2,3},new double[]{1,1,2,3}, new double[]{1,1,2,3}};
        double[][] x = MatrixUtils.getx(matrix);
        double[][] actual = MatrixUtils.getX(x);
        assertEquals(expected[0].length, actual[0].length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(Arrays.toString(expected[i]), Arrays.toString(actual[i]));
            System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected[i]), Arrays.toString(actual[i])));
        }
    }

    @Test
    public void transposeTest() {
        double[][] matrix = new double[][]{new double[]{1,2,3},new double[]{1,2,3}, new double[]{1,2,3}};
        double[][] expected = new double[][]{new double[]{1,1,1},new double[]{2,2,2}, new double[]{3,3,3}};
        double[][] actual = MatrixUtils.transpose(matrix);
        assertEquals(expected[0].length, actual[0].length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(Arrays.toString(expected[i]), Arrays.toString(actual[i]));
            System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected[i]), Arrays.toString(actual[i])));
        }
    }

    @Test
    public void multTest() {
        double[][] matrix1 = MatrixUtils.transpose(new double[][]{new double[]{1,2,3},new double[]{3,7,6}});
        double[][] matrix2 = new double[][]{new double[]{1,9,4},new double[]{2,2,5}};
        double[][] expected = new double[][]{new double[]{7,15,19},new double[]{16,32,43}, new double[]{15,39,42}};
        double[][] actual = MatrixUtils.mult(matrix1, matrix2);
        assertEquals(expected[0].length, actual[0].length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(Arrays.toString(expected[i]), Arrays.toString(actual[i]));
            System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected[i]), Arrays.toString(actual[i])));
        }
    }

    @Test
    public void multTest2() {
        double[] input = new double[]{1,2,3};
        double[] expected = new double[]{2,4,6};
        double[] actual = MatrixUtils.mult(2,input);
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    public void inverseTest() {
        double[][] matrix = new double[][]{new double[]{500,1,1000},new double[]{750,2,1300}};
        double[][] expected = new double[][]{new double[]{0.85000,-0.40000},new double[]{-0.40000,0.20000}};
        double[][] test = MatrixUtils.getX(matrix);
        double[][] test2 = MatrixUtils.mult(MatrixUtils.transpose(test), test);
        double[][] actual = MatrixUtils.inverse(test2);
        assertEquals(expected[0].length, actual[0].length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(Arrays.toString(expected[i]), Arrays.toString(actual[i]));
            System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected[i]), Arrays.toString(actual[i])));
        }
        matrix = new double[][]{new double[]{-0.50000,1.00000,1000},new double[]{0.50000,2.00000,1300}};
        double[][] X = MatrixUtils.getX(matrix);
        double[][] xT = MatrixUtils.transpose(X);
        double[] y = MatrixUtils.getColVector(matrix, matrix[0].length - 1);//get a vector containing the y column
        double[][] x2 = MatrixUtils.mult(xT, X);
        double[][] inv = MatrixUtils.inverse(x2);
        double[][] mX = MatrixUtils.mult(inv, xT);
        double[] theta =MatrixUtils.mult(mX, y);
        System.out.println(Arrays.toString(theta));


    }

    @Test
    public void eyeTest() {
        double[][] matrix1 = new double[][]{new double[]{500,1},new double[]{750,2}};
        double[][] matrix2 = new double[][]{new double[]{500,1,1000},new double[]{750,2,1300}, new double[]{750,2,1300}};
        double[][] expected1 = new double[][]{new double[]{1,0},new double[]{0,1}};
        double[][] expected2 = new double[][]{new double[]{1,0,0},new double[]{0,1,0}, new double[]{0,0,1}};
        double[][] actual1 = MatrixUtils.eye(matrix1);
        double[][] actual2 = MatrixUtils.eye(matrix2);
        assertEquals(expected1[0].length, actual1[0].length);
        for (int i = 0; i < actual1.length; i++) {
            assertEquals(Arrays.toString(expected1[i]), Arrays.toString(actual1[i]));
            System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected1[i]), Arrays.toString(actual1[i])));
        }
        assertEquals(expected2[0].length, actual2[0].length);
        for (int i = 0; i < actual2.length; i++) {
            assertEquals(Arrays.toString(expected2[i]), Arrays.toString(actual2[i]));
            System.out.println(String.format("Expected: %s \nActual: %s", Arrays.toString(expected2[i]), Arrays.toString(actual2[i])));
        }

    }

    @Test(expected = NonConformantArgumentException.class)
    public void subtractTestBadArgument() {
        MatrixUtils.sub(new double[]{1,2,3,4}, new double[]{1,2,3});
    }

    @Test
    public void subtractTest() {
        double[] expected = new double[]{-4,-4,-4,-4};
        double[] actual = MatrixUtils.sub(new double[]{1,2,3,4}, new double[]{5,6,7,8});
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    public void powTest() {
        double[] expected = new double[]{1,4,9};
        double[] actual = MatrixUtils.pow(2, new double[]{1,2,3});
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

}
