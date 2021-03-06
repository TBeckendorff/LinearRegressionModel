/**
 * Created by Trenton Beckendorff on 8/14/2015.
 */
public class Main {

    public static void main(String[] args) {
        String path = args[0];
        MultipleRegression lm = new MultipleRegression(path);

        MatrixOperations ops = new MatrixOperations();
        System.out.println("Inputs: \n");
        MatrixOperations.printMatrix(lm.getInputs());
        System.out.println("Outputs: \n");
        MatrixOperations.printMatrix(lm.getOutputs());
        System.out.println("Weights: \n");
        MatrixOperations.printMatrix(lm.getWeights());
        System.out.println("Number of Features: " + lm.getNumFeatures());
        System.out.println("Number of Training Examples: " + lm.getNumTrainingExamples());
    }
}
