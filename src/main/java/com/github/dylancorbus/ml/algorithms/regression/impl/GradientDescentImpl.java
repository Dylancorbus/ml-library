package com.github.dylancorbus.ml.algorithms.regression.impl;

import com.github.dylancorbus.ml.algorithms.regression.Regression;
import com.github.dylancorbus.ml.utils.matrices.MatrixUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class GradientDescentImpl implements Regression {
    private double alpha;
    private double[] theta;
    private int itrs = 100;
    private boolean isConverged = false;
    private  double convergenceTh = 0.001;

    public GradientDescentImpl(double learnRate) {
        this.alpha = learnRate;
    }

    /**
     * Runs the gradient descent algorithm and estimates the best representative
     * function for the data. Last column must contain the y values
     *
     * @param matrix - matrix containing rows and columns. x values can go in columns 0-(n-1)
     *               y values must be in the last column n.
     */
    public double fit(final double[][] matrix) {
        theta = new double[matrix[0].length];
        double[][] x = MatrixUtils.getx(matrix);
        double[][] X = MatrixUtils.getX(x);
        double[] y = MatrixUtils.getColVector(matrix, matrix[0].length - 1);//get a vector containing the y column
        System.out.println(String.format("MSE before descent: value: %.10f", meanSquaredError(X, y)));
        normalizeX(X);
        int i = 0;
        while (this.itrs > i++) {
            var mse_before = meanSquaredError(X, y);
            //h = X * theta
            double[] h = MatrixUtils.mult(X, theta);
            //std_err = h - y distance from prediction and actual value hence its called err
            double[] std_err = MatrixUtils.sub(h, y);
            //theta = theta - (alpha/m) * X' * std_err
            theta = MatrixUtils.sub(theta, MatrixUtils.mult((this.alpha / matrix.length), MatrixUtils.mult(MatrixUtils.transpose(X), std_err)));
            var mse_after = meanSquaredError(X, y);
            this.isConverged = (mse_before - mse_after) < this.convergenceTh;
//            System.out.println(String.format("MSE: ITR #%d value: %.10f", i, mse_after));
        }
        return meanSquaredError(X, y);

    }

    /**
     * Calculates the mean squared error for all points in the dataset.
     *
     * @param X - x values
     * @param y - y values
     * @return - double: mean squared error
     */
    private double meanSquaredError(double[][] X, double[] y) {
        //h = X * theta - y
        double[] h = MatrixUtils.sub(MatrixUtils.mult(X, this.theta), y);
        //sum = sum(h .^ 2)
        double sum = Arrays.stream(MatrixUtils.pow(2, h)).sum();
        //J = 1/(2*m)* sum
        return 1 / (2 * y.length) * sum;
    }

    private void normalizeX(double[][] x) {
        IntStream.range(1, x[0].length)
                .forEach(col -> {
                    double[] avgNRange = MatrixUtils.getRangeAndAverage(x, col);
                    double range = avgNRange[0];
                    double avg = avgNRange[1];
                    IntStream.range(0, x.length)
                            .forEach(row -> x[row][col] = (x[row][col] * avg) / range);
                });
    }

    /**
     * Predicts the y value for x
     *
     * @param x - x value
     * @return - prediction
     */
    public double[] predict(double[][] x) {
        double[][] X = MatrixUtils.getX(x);
        return MatrixUtils.mult(X, this.theta);
    }

    public static void main(String[] args) {
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
//
        GradientDescentImpl gd = new GradientDescentImpl(0.001);
        System.out.println(gd.fit(matrix1));
        System.out.println(Arrays.toString(gd.theta));
        System.out.println(Arrays.toString(gd.predict(new double[][]{new double[]{5.8598, 1}})));



        FileInputStream inputStream = null;
        Scanner sc = null;
        double[][] matrix = new double[10][];
        try {
            inputStream = new FileInputStream("/Users/dylancorbus/Desktop/machine-learning-ex1/ex1/ex1data2.txt");
            sc = new Scanner(inputStream, "UTF-8");
            int i = 0;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] rowStr = line.split(",");
                double[] row = Arrays.stream(rowStr).mapToDouble(str -> Double.valueOf(str)).toArray();
                matrix[i++] = row;
                if(i >= matrix.length) {
                    double[][] newMatrix = new double[matrix.length * 2][];
                    System.arraycopy(matrix, 0, newMatrix, 0, matrix.length);
                    matrix = newMatrix;
                }

            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
            matrix = Arrays.stream(matrix)
                    .filter(r -> (r != null && r.length > 0))
                    .toArray(double[][]::new);
        }

        gd = new GradientDescentImpl(0.001);
        System.out.println(gd.fit(matrix));
//        1203,3,239500
        System.out.println(Arrays.toString(gd.theta));
        System.out.println(Arrays.toString(gd.predict(new double[][]{new double[]{1203,3}})));
    }

}