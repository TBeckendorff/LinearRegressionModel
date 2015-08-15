/**
 * Created by Trenton Beckendorff on 8/14/2015.
 */
public class MatrixOperations {
    public static void printMatrix(double[][] matrix) {
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix[j].length; i++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println("\n");
        }
    }

    // TODO: Check for Proper Output of transposeInputMatrix function.
    private double[][] transposeInputMatrix(double[][] matrixA) {
        double[][] matrixB = new double[matrixA.length][matrixA[0].length];

        for (int j = 0; j < matrixA[0].length; j++) {
            for (int i = 0; i < matrixA.length; i++) {
                matrixB[j][i] = matrixA[i][j];
            }
        }

        return matrixB;
    }

    public double[][] multiplyMatrices(double[][] matrixA, double[][] matrixB) {
        double[][] matrixC;

        int numRowsA = matrixA.length;
        int numColumnsA = matrixA[0].length;
        int numRowsB = matrixB.length;
        int numColumnsB = matrixB[0].length;

        if (numColumnsA == numRowsB) {
            matrixC = new double[numRowsA][numColumnsB];
        } else {
            System.out.println("Error: Unable to Multiply Specified Matrices..Invalid Dimensions");
            matrixC = null;
            return matrixC;
        }

        for (int j = 0; j < numRowsA; j++) {
            for (int i = 0; i < numColumnsB; i++) {
                for (int k = 0; k < numColumnsA; k++) {
                    matrixC[j][i] += matrixA[j][k] * matrixB[k][i];
                }
            }
        }

        return matrixC;
    }
}
