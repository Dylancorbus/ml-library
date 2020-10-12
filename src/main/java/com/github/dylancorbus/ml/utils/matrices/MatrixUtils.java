package com.github.dylancorbus.ml.utils.matrices;

import java.util.stream.IntStream;

public class MatrixUtils {

    public static double[] linearRegression(int[][] matrix, int[] vector) {
        int rows = matrix.length;
        int columns = vector.length;
        return IntStream.range(0, rows)
                .mapToDouble(row -> IntStream.range(0, columns)
                        .mapToDouble(col -> matrix[row][col] * vector[col])
                        .sum()).toArray();
    }


    public static double[] getColVector(int[][] matrix, int col) {
        double[] vector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            vector[i] = matrix[i][col - 1];
        }
        return vector;
    }

    public static double[] getRowVector(int[][] matrix, int row) {
        double[] vector = new double[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            vector[i] = matrix[row - 1][i];
        }
        return vector;
    }


}
