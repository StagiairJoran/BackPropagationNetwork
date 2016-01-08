package model;


import javafx.concurrent.Task;

import java.util.Observable;
import java.util.Random;

/**
 * Created by Joachim De Schryver & Joran De Boever
 * on 17/11/15.
 */
public class NeuraalNetwerk extends Observable {

    private double[] inputWaarden;

    private double[][] eersteAxonen; // input-to-hidden

    private double[] tempHiddens = new double[4];
    private double[] hiddenWaarden = new double[4];
    private double[] hiddenBiasWaarden = new double[4];

    private double[][] tweedeAxonen = new double[4][2]; // hidden-to-output

    private double[] outputWaarden = new double[2];
    private double[] outputBiasWaarden = new double[2];

    private double[] targets = new double[2];

    private double[] outputErrors = new double[2];
    private double[] hiddenErrors = new double[4];

    double[][] eersteAxonenPreviousDelta = new double[3][4];
    double[][] tweedeAxonenPreviousDelta = new double[4][2];

    double[] hiddenPreviousBiasesDelta = new double[4];
    double[] outputPreviousBiasesDelta = new double[2];

    //algoritme implementatie parameters
    private int epoch = 0;

    private int maxEpoch = 10000;

    private double error = 0;

    private double errorThreshold = 0.000001;
    private double momentum = 0.1;
    private double learningRate = 0.5;


    private double progressTeller = 0;
    private double progressError = 0;
    private double progressTargets = 0;

    public NeuraalNetwerk() {

    }

    public void initializeLetterNetwerk() {
        double[] inputWaardenA = new double[]{0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1};

        inputWaarden = inputWaardenA;
        eersteAxonen = new double[64][4];
        eersteAxonenPreviousDelta = new double[64][4];

        double teller = 0.0001;
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 4; j++) {
                eersteAxonen[i][j] = teller;
                teller += 0.0001;
            }
        }

        init();
    }

    public void initializeSimpleNetwerk() {
        inputWaarden = new double[3];
        eersteAxonen = new double[3][4];

        inputWaarden[0] = 1.0;
        inputWaarden[1] = -2.0;
        inputWaarden[2] = 3.0;

        eersteAxonen[0][0] = 0.001;
        eersteAxonen[0][1] = 0.002;
        eersteAxonen[0][2] = 0.003;
        eersteAxonen[0][3] = 0.004;

        eersteAxonen[1][0] = 0.005;
        eersteAxonen[1][1] = 0.006;
        eersteAxonen[1][2] = 0.007;
        eersteAxonen[1][3] = 0.008;

        eersteAxonen[2][0] = 0.009;
        eersteAxonen[2][1] = 0.010;
        eersteAxonen[2][2] = 0.011;
        eersteAxonen[2][3] = 0.012;
        error = 1;
        init();
    }

    public void init() {


        hiddenWaarden[0] = 0.0310;
        hiddenWaarden[1] = 0.0340;
        hiddenWaarden[2] = 0.0370;
        hiddenWaarden[3] = 0.0400;

        hiddenBiasWaarden[0] = 0.013;
        hiddenBiasWaarden[1] = 0.014;
        hiddenBiasWaarden[2] = 0.015;
        hiddenBiasWaarden[3] = 0.016;

        tweedeAxonen[0][0] = 0.017;
        tweedeAxonen[0][1] = 0.018;

        tweedeAxonen[1][0] = 0.019;
        tweedeAxonen[1][1] = 0.020;

        tweedeAxonen[2][0] = 0.021;
        tweedeAxonen[2][1] = 0.022;

        tweedeAxonen[3][0] = 0.023;
        tweedeAxonen[3][1] = 0.024;

        outputWaarden[0] = 0.5070;
        outputWaarden[1] = 0.5073;

        outputBiasWaarden[0] = 0.025;
        outputBiasWaarden[0] = 0.026;

        Random r = new Random();
        targets[0] = r.nextDouble();
        targets[1] = r.nextDouble();

        outputErrors[0] = 0.00;
        outputErrors[1] = 0.00;

        hiddenErrors[0] = 0.00;
        hiddenErrors[1] = 0.00;
        hiddenErrors[2] = 0.00;
        hiddenErrors[3] = 0.00;

        for (int inputTeller = 0; inputTeller < inputWaarden.length; inputTeller++) {
            for (int hiddenTeller = 0; hiddenTeller < hiddenWaarden.length; hiddenTeller++) {
                eersteAxonenPreviousDelta[inputTeller][hiddenTeller] = 0.00;
            }
        }

        for (int hiddenTeller = 0; hiddenTeller < hiddenWaarden.length; hiddenTeller++) {
            for (int outputTeller = 0; outputTeller < outputWaarden.length; outputTeller++) {
                tweedeAxonenPreviousDelta[hiddenTeller][outputTeller] = 0.00;
            }
        }

        for (int i = 0; i < hiddenPreviousBiasesDelta.length; i++) {
            hiddenPreviousBiasesDelta[i] = 0.00;
        }

        for (int i = 0; i < outputPreviousBiasesDelta.length; i++) {
            outputPreviousBiasesDelta[i] = 0.00;
        }

        progressTargets = 0;
        progressError = 0;
        progressTeller = 0;
    }

    public double[] getInputWaarden() {
        return inputWaarden;
    }

    public double[][] getEersteAxonen() {
        return eersteAxonen;
    }

    public double[] getHiddenWaarden() {
        return hiddenWaarden;
    }

    public double[] getHiddenBiasWaarden() {
        return hiddenBiasWaarden;
    }

    public double[][] getTweedeAxonen() {
        return tweedeAxonen;
    }

    public double[] getOutputWaarden() {
        return outputWaarden;
    }

    public double[] getOutputBiasWaarden() {
        return outputBiasWaarden;
    }

    public double[] getTargets() {
        return targets;
    }

    public double[] getOutputErrors() {
        return outputErrors;
    }

    public double[] getHiddenErrors() {
        return hiddenErrors;
    }

    public double getProgressTeller() {
        return progressTeller;
    }

    public double getProgressError() {
        return progressError;
    }

    public double getProgressTargets() {
        return progressTargets;
    }

    public double getError() {
        return error;
    }

    public double getErrorThreshold() {
        return errorThreshold;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public void setErrorThreshold(double errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    public int getEpoch() {
        return epoch;
    }

    public int getMaxEpoch() {
        return maxEpoch;
    }

    public static double SigmoidFunction(double x) {
        if (x < -45.0)
            return 0.0;
        if (x > 45.0)
            return 1.0;
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public static double HyperTanFunction(double x) {
        if (x < -45.0)
            return -1.0;
        if (x > 45.0)
            return 1.0;
        return Math.tanh(x);
    }


    public void startBackPropagation() {
        epoch = 0;
        error = 1;
        while (epoch < maxEpoch && error > errorThreshold) {
            outputWaarden = computeOutputs();
            backPropagationFlow();
            error = (targets[0] - outputWaarden[0]) + (targets[1] - outputWaarden[1]);
            error = Math.abs(error);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            setChanged();
            notifyObservers();
            epoch++;
            progressTeller = ((double) epoch) / maxEpoch;
            progressError = 1 - (error - 0.0001);

            double iets = 1 - ((outputWaarden[0] - targets[0]) * 1.96); //nooit groter dan 0.51
            double ander = 1 - ((outputWaarden[1] - targets[1]) * 1.96);
            double ja = (iets + ander) / 2;
            progressTargets = ja;
        }

    }

    public Task getTask() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                // updateProgress(1, 100);
                startBackPropagation();
                return null;
            }

        };
        return task;
    }

    private double[] computeOutputs() {
        //calculating hidden neurons
        for (int hiddenTeller = 0; hiddenTeller < hiddenWaarden.length; hiddenTeller++) {
            hiddenWaarden[hiddenTeller] = hiddenBiasWaarden[hiddenTeller];
            for (int inputTeller = 0; inputTeller < inputWaarden.length; inputTeller++) {
                hiddenWaarden[hiddenTeller] += inputWaarden[inputTeller] * eersteAxonen[inputTeller][hiddenTeller];
            }
            tempHiddens[hiddenTeller] = HyperTanFunction(hiddenWaarden[hiddenTeller]);

        }

        //calculating output neurons
        double[] tempOutputWaarden = new double[2];
        for (int outputTeller = 0; outputTeller < outputWaarden.length; outputTeller++) {
            Double neuronTemp = outputBiasWaarden[outputTeller];
            for (int hiddenTeller = 0; hiddenTeller < tempHiddens.length; hiddenTeller++) {
                neuronTemp += tempHiddens[hiddenTeller] * tweedeAxonen[hiddenTeller][outputTeller];
            }
            tempOutputWaarden[outputTeller] = SigmoidFunction(neuronTemp);
        }
        return tempOutputWaarden;
    }

    private void backPropagationFlow() {
        //calculating outputErrors
        for (int i = 0; i < outputErrors.length; i++) {
            outputErrors[i] = ((1 - outputWaarden[i]) * outputWaarden[i]) * (targets[i] - outputWaarden[i]);
        }

        //calculating hiddenErrors
        for (int hiddenTeller = 0; hiddenTeller < tempHiddens.length; hiddenTeller++) {
            hiddenErrors[hiddenTeller] = ((1 + tempHiddens[hiddenTeller]) * (1 - tempHiddens[hiddenTeller])) * ((outputErrors[0] * tweedeAxonen[hiddenTeller][0]) + (outputErrors[1] * tweedeAxonen[hiddenTeller][1]));
        }

        //change eerste axonen weights
        for (int hiddenTeller = 0; hiddenTeller < tempHiddens.length; hiddenTeller++) {
            for (int inputTeller = 0; inputTeller < inputWaarden.length; inputTeller++) {
                double delta = learningRate * hiddenErrors[hiddenTeller] * inputWaarden[inputTeller];                                 //Stap 1
                eersteAxonen[inputTeller][hiddenTeller] += delta;                                                                      //Stap 2
                eersteAxonen[inputTeller][hiddenTeller] += momentum * eersteAxonenPreviousDelta[inputTeller][hiddenTeller];           //Stap 3
                eersteAxonenPreviousDelta[inputTeller][hiddenTeller] = delta;                                                           //Stap 4

            }
        }

        //update hidden biasses
        for (int hiddenTeller = 0; hiddenTeller < tempHiddens.length; hiddenTeller++) {
            double delta = learningRate * hiddenErrors[hiddenTeller];                                                                  //stap 1
            hiddenBiasWaarden[hiddenTeller] += delta;                                                                                  //Stap 2
            hiddenBiasWaarden[hiddenTeller] += momentum * hiddenPreviousBiasesDelta[hiddenTeller];                                     //Stap 3
            hiddenPreviousBiasesDelta[hiddenTeller] = delta;                                                                           //Stap 4
        }

        //update hidden layer weights
        for (int hiddenTeller = 0; hiddenTeller < tweedeAxonen.length; hiddenTeller++) {
            for (int outputTeller = 0; outputTeller < outputWaarden.length; outputTeller++) {
                double delta = learningRate * outputErrors[outputTeller] * hiddenWaarden[hiddenTeller];                                 //Stap 1
                tweedeAxonen[hiddenTeller][outputTeller] += delta;                                                                      //Stap 2
                tweedeAxonen[hiddenTeller][outputTeller] += momentum * tweedeAxonenPreviousDelta[hiddenTeller][outputTeller];           //Stap 3
                tweedeAxonenPreviousDelta[hiddenTeller][outputTeller] = delta;                                                          //Stap 4
            }
        }

        //update output biasses
        for (int outputTeller = 0; outputTeller < outputWaarden.length; outputTeller++) {
            double delta = learningRate * outputErrors[outputTeller];                                                                  //stap 1
            outputBiasWaarden[outputTeller] += delta;                                                                                  //Stap 2
            outputBiasWaarden[outputTeller] += momentum * outputPreviousBiasesDelta[outputTeller];                                     //Stap 3
            outputPreviousBiasesDelta[outputTeller] = delta;                                                                           //Stap 4
        }
    }


}
