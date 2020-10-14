package com.github.dylancorbus.ml.algorithms.regression;

import java.io.FileWriter;
import java.io.IOException;

public interface Regression {


    /**
     * Runs the algorithm and estimates the best representative
     * function for the data. Last column must contain the y values
     *
     * @param x - matrix containing rows and columns. x values can go in columns 0-(n-1)
     *               y values must be in the last column n.
     * @return - value of the minimized j function
     */
    double fit(double[][] x);

    /**
     * Predicts the y value for x
     * @param x - x value
     * @return - prediction
     */
    double[] predict(double[][] x);

    void save(String pathName);
    void load(String pathName);
}
