package com.github.dylancorbus.ml.algorithms.regression;

public interface Model {


    /**
     * Runs the gradient descent algorithm and estimates the best representative
     * function for the data.
     * @param x
     * @param y
     */
    public void fit(double[] x, double[] y);

    /**
     * Predicts the y value for x
     * @param x - x value
     * @return - prediction
     */
    public double[] predict(double x);
}
