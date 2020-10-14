package com.github.dylancorbus.ml.utils.matrices;

import com.github.dylancorbus.ml.exceptions.NonConformantArgumentException;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MatrixUtils {

    public static double[][] eye(double[][] matrix) {
        if(matrix.length != matrix[0].length) {
            throw new NonConformantArgumentException(
                    String
                            .format("error: non-square matrix: argument dimensions (op1 is %dx%d)",
                                    matrix.length, matrix[0].length));
        }
        double[][] eye = new double[matrix.length][matrix[0].length];
        IntStream.range(0,matrix.length).forEach(i -> eye[i][i] = 1);
        return eye;
    }


    public static double[] getColVector(double[][] matrix, int col) {
        double[] vector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            vector[i] = matrix[i][col];
        }
        return vector;
    }

    public static double[] getRowVector(double[][] matrix, int row) {
        double[] vector = new double[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            vector[i] = matrix[row][i];
        }
        return vector;
    }

    public static double[][] getX(double[][] matrix) {
        double[][] features = new double[matrix.length][matrix[0].length + 1];
        IntStream.range(0, matrix.length)
                .forEach(row -> IntStream.range(0, 1)
                        .forEach(col -> {
                            features[row][col] = 1;
                        }));
        IntStream.range(0, matrix.length)
                .forEach(row -> IntStream.range(0, matrix[row].length)
                        .forEach(col -> {
                            features[row][col + 1] = matrix[row][col];
                        }));
        return features;
    }

    public static double[][] getx(double[][] matrix) {
        double[][] features = new double[matrix.length][matrix[0].length - 1];
        IntStream.range(0, matrix.length)
                .forEach(row -> IntStream.range(0, matrix[row].length - 1)
                        .forEach(col -> {
                            features[row][col] = matrix[row][col];
                        }));
        return features;
    }


    public static double[][] transpose(double[][] matrix) {
        double[][] X = new double[matrix[0].length][matrix.length];
        IntStream.range(0, matrix[0].length)
                .forEach(col -> IntStream.range(0, matrix.length)
                        .forEach(row -> X[col][row] = matrix[row][col]));
        return X;
    }

    public static double[][] mult(double[][] matrixT, double[][] matrix) {
        double[][] result = Arrays.stream(matrixT).map(r ->
                IntStream.range(0, matrix[0].length).mapToDouble(i ->
                        IntStream.range(0, matrix.length).mapToDouble(j -> r[j] * matrix[j][i]).sum()
                ).toArray()).toArray(double[][]::new);

        return result;
    }

    public static double[] mult(double num, double[] vector) {
        return Arrays.stream(vector).map(x -> x * num).toArray();
    }

    public static double[] mult(double[][] matrix, double[] vector) {
        int rows = matrix.length;
        int columns = vector.length;
        return IntStream.range(0, rows)
                .mapToDouble(row -> IntStream.range(0, columns)
                        .mapToDouble(col -> matrix[row][col] * vector[col])
                        .sum()).toArray();
    }

    public static double[] sub(double[] vector1, double[] vector2) {
        if(vector1.length != vector2.length) {
            throw new NonConformantArgumentException(
                    String
                            .format("error: operator -: nonconformant arguments (op1 is %dx1, op2 is %dx1)",
                                    vector1.length, vector2.length));
        }
        return IntStream
                .range(0, vector1.length)
                .mapToDouble(i -> vector1[i] - vector2[i])
                .toArray();
    }


    private static double determinant(double[][] m) {
//        if (matrix.length != matrix[0].length)
//            throw new IllegalStateException("invalid dimensions");
//
//        if (matrix.length == 2)
//            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < m[0].length; i++) {
            double[][] matrix = minor(m, 0, i);
            if (matrix.length != matrix[0].length)
                throw new IllegalStateException("invalid dimensions");
            double x = 0;
            if (matrix.length == 2) {
                x = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            }
            det += Math.pow(-1, i) * matrix[0][i]
//                    * determinant(matrix);
                    * x;

        }
        return det;
    }

    public static double[][] inverse(double[][] matrix) {
        double[][] inverse = new double[matrix.length][matrix.length];

        // minors and cofactors
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j)
                        * determinant(minor(matrix, i, j));

        // adjugate and determinant
        double det = 1.0 / determinant(matrix);
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }
        }

        return inverse;
    }

    private static double[][] minor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return minor;
    }

    public static double[] getRangeAndAverage(double[][] matrix, int c) {
        double max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, avg = 0;
            for (int r = 0; r < matrix[c].length; r++) {
                double current = matrix[r][c];
                avg += current;
                if(current > max) max = current;
                if(current < min) min = current;
            }
        return new double[]{max - min, avg/matrix.length};
    }

    public static double[] pow(double num, double[] vector) {
        return Arrays.stream(vector).map(x -> Math.pow(x,num)).toArray();
    }
}
