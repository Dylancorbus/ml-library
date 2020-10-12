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


    public static double[] getVector(int[][] matrix, int col) {
        double[] vector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            vector[i] = matrix[i][col - 1];
        }
        return vector;
    }


}
