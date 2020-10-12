class GradientDescent {
    alpha;
    theta0;
    theta1;
    maxItr = 1000;
    convergenceTh = 0.0001;
    isConverged = false;

    constructor(learnRate) {
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
    dj(theta, x, y) {
        let sum = 0;
        for (let i = 0; i < x.length; i++) {
            let diff = this.hypothesis(x[i]) - y[i];
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
    meanSquaredError(x, y) {
        let sum = 0;
        for (let i = 0; i < x.length; i++) {
            sum += Math.pow(this.hypothesis(x[i]) - y[i], 2);
        }
        return sum / (x.length * 2);
    }

    /**
     * Returns the regression function
     * @param x - x value
     * @return - predicted y value
     */
    hypothesis(x) {
        return this.theta0 + this.theta1 * x;
    }

    /**
     * Runs the gradient descent algorithm and estimates the best representative
     * function for the data.
     * @param x
     * @param y
     */
    fit(x, y) {
        let itr = 0;
        while (!this.isConverged && this.maxItr > itr++) {
            let mse_before = this.meanSquaredError(x, y);
            // The descent step
            var temp0 = this.theta0 - (this.alpha * this.dj(0, x, y));
            var temp1 = this.theta1 - (this.alpha * this.dj(1, x, y));
            this.theta0 = temp0;
            this.theta1 = temp1;
            this.isConverged = (mse_before - this.meanSquaredError(x, y) < this.convergenceTh);
        }
    }

    /**
     * Predicts the y value for x
     * @param x - x value
     * @return - prediction
     */
    predict(x) {
        return [x, this.hypothesis(x)];
    }
}


gradientDescent = new GradientDescent(0.02);
//housing price prediction based on sqft
x = [5, 6.5, 7, 7.5, 8.5, 10];//sqft * 100
y = [15, 16, 16.7, 17.5, 22, 26];//price * 100
gradientDescent.fit(x, y);
console.log(gradientDescent.predict(10));