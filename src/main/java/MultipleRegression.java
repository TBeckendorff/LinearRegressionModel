import Jama.Matrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Trenton Beckendorff on 8/14/2015.
 */
public class MultipleRegression {
    private int numFeatures;
    private int numTrainingExamples;
    private double[][] Weights;
    private double[][] Inputs;
    private double[][] Outputs;
    private String filePath;
    private HashMap<Integer, List<Double>> data;
    private double Bias;

    public MultipleRegression(String inputFilePath) {
        this.filePath = inputFilePath;
        this.data = readInputCSV();
        this.numFeatures = data.size();
        this.numTrainingExamples = data.get(0).size();
        this.Bias = 1;
        this.Inputs = generateInputMatrix();
        this.Outputs = generateOutputMatrix();
        this.Weights = predictWeights();
    }

    private double[][] predictWeights() {
        double[][] Weights;
        Matrix inputsMatrix = new Matrix(Inputs);
        Matrix outputsMatrix = new Matrix(Outputs);
        Matrix trInputsMatrix = inputsMatrix.transpose();

        //Weights = (Inputs(Transposed) * Inputs)^-1 (Inputs(Transposed) * Outputs)
        Weights = ((trInputsMatrix.times(inputsMatrix)).inverse()).times(trInputsMatrix).times(outputsMatrix).getArray();
        return Weights;
    }

    private double[][] generateOutputMatrix() {
        double[][] Outputs = new double[numTrainingExamples][1];
        for (int i = 0; i < data.get(numFeatures - 1).size(); i++) {
            Outputs[i][0] = data.get(numFeatures - 1).get(i);
        }
        return Outputs;
    }

    public int getNumFeatures() {
        return numFeatures;
    }

    public int getNumTrainingExamples() {
        return numTrainingExamples;
    }

    public double[][] getWeights() {
        return Weights;
    }

    public double[][] getInputs() {
        return Inputs;
    }

    public double[][] getOutputs() {
        return Outputs;
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<Integer, List<Double>> getData() {
        return data;
    }

    public double getBias() {
        return Bias;
    }

    private double[][] generateInputMatrix() {
        double[][] Inputs = new double[numTrainingExamples][numFeatures];

        for (int i = 0; i < numFeatures; i++) {
            for (int j = 0; j < numTrainingExamples; j++) {
                if (i == 0) {
                    Inputs[j][i] = 1;
                } else {
                    Inputs[j][i] = data.get(i - 1).get(j);
                }

            }
        }

        return Inputs;
    }

    private HashMap<Integer, List<Double>> readInputCSV() {
        HashMap<Integer, List<Double>> data = new HashMap<Integer, List<Double>>();
        try {
            String line;
            int lineNumber = 0;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                if (lineNumber > 0) {
                    String[] columns = line.split(",");
                    for (int i = 0; i < columns.length; i++) {
                        if (data.containsKey(i) == true) {
                            data.get(i).add(Double.parseDouble(columns[i]));
                        } else {
                            List<Double> column = new ArrayList<Double>();
                            column.add(Double.parseDouble(columns[i]));
                            data.put(i, column);
                        }
                    }
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


}

