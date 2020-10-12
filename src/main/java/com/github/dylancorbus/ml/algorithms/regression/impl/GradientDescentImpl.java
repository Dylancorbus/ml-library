package com.github.dylancorbus.ml.algorithms.regression.impl;

import com.github.dylancorbus.ml.models.Model;

public class GradientDescentImpl implements Model {
    private double alpha;
    private double theta0;
    private double theta1;
    private int maxItr = 1000;
    private  double convergenceTh = 0.0001;
    private  boolean isConverged = false;

    public GradientDescentImpl(double learnRate) {
        this.alpha = learnRate;
        this.theta0 = 0;
        this.theta1 = 0;
    }

    /**
     * The partial derivative of the cost function.
     * @param theta - 0 for theta0, 1 for theta 1.
     * @param x - x values
     * @param y - y values
     * @return - double: derivative of the cost function
     */
    private double dj(int theta, double[] x, double[] y) {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            double diff = this.hypothesis(x[i]) - y[i];
            sum += theta > 0 ? diff * x[i] : diff;//for theta1 you need to multiply by x value
        }
        return sum / x.length;
    }

    /**
     * Calculates the mean squared error for all points in the dataset.
     * @param x - x values
     * @param y - y values
     * @return - double: mean squared error
     */
    private double meanSquaredError(double[] x, double[] y) {
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += Math.pow(this.hypothesis(x[i]) - y[i], 2);
        }
        return sum / (x.length * 2);
    }

    /**
     * Returns the regression function
     * @param x - x value
     * @return - predicted y value
     */
    private double hypothesis(double x) {
        return this.theta0 + this.theta1 * x;
    }

    /**
     * Runs the gradient descent algorithm and estimates the best representative
     * function for the data.
     * @param x
     * @param y
     */
    public void fit(double[] x, double[] y) {
        int itr = 0;
        while (!this.isConverged && this.maxItr > itr++) {
            double mse_before = meanSquaredError(x, y);
            // The descent step
            var temp0 = this.theta0 - (this.alpha * this.dj(0, x, y));
            var temp1 = this.theta1 - (this.alpha * this.dj(1, x, y));
            this.theta0 = temp0;
            this.theta1 = temp1;
            this.isConverged = (mse_before - meanSquaredError(x, y) < convergenceTh);
        }
    }

    /**
     * Predicts the y value for x
     * @param x - x value
     * @return - prediction
     */
    public double[] predict(double x) {
        return new double[]{x, hypothesis(x)};
    }
}