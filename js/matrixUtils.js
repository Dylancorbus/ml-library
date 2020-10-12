class MatrixUtils {

    linearRegression(matrix, vector) {
        let rows = matrix.length;
        let cols = vector.length;
        let v = [];
        for (let row = 0; row < rows; row++) {
            let sum = 0;
            for (let col = 0; col < cols; col++) {
                sum += matrix[row][col] * vector[col];
                console.log(sum);
            }
            v.push(sum);

        }
        return v;
    }


    getColVector(matrix, col) {
        let vector = [];
        for (let i = 0; i < matrix.length; i++) {
            vector.push(matrix[i][col - 1]);
        }
        return vector;
    }

    getRowVector(matrix, row) {
        let vector = [];
        for (let i = 0; i < matrix[0].length; i++) {
            vector.push(matrix[row - 1][i]);
        }
        return vector;
    }
}

matrixUtils = new MatrixUtils();
//housing price prediction based on sqft
x = [1, 2];//sqft * 100
y = [[1, 2], [3, 4], [5, 6]];//price * 100
console.log(matrixUtils.linearRegression(y, x));