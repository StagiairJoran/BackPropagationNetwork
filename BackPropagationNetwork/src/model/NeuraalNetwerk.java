package model;


import javafx.concurrent.Task;

import java.util.Observable;
import java.util.Random;

/**
 * Created by Joachim De Schryver & Joran De Boever
 * on 17/11/15.
 */
public class NeuraalNetwerk extends Observable {

    private double[] inputWaarden = new double[3];

    private double[][] eersteAxonen = new double[3][4]; // input-to-hidden

    private double[] tempHiddens = new double[4];
    private double[] hiddenWaarden = new double[4];
    private double[] hiddenBiasWaarden = new double[4];

    private double[][] tweedeAxonen = new double[4][2]; // hidden-to-output

    private double[] outputWaarden = new double[2];
    private double[] outputBiasWaarden = new double[2];

    private double[] targets = new double[2];

    private double[] outputErrors = new double[2];
    private double[] hiddenErrors = new double[4];

    //algoritme implementatie parameters
    private int epoch = 0;

    private int maxEpoch = 1000;

    private double error = 0;

    private double errorThreshold = 0.0001;

    private double momentum;

    private double learningRate = 0.5;


    private double progress = 0;

    public NeuraalNetwerk() {

    }

    public void init() {
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

    public double getProgress() {
        return progress;
    }

    public double getError() {
        return error;
    }

    public double getErrorThreshold() {
        return errorThreshold;
    }

    public static double SigmoidFunction(double x) {
        if (x < -45.0)
            return 0.0;
        if (x > 45.0)
            return 1.0;
        return 1.0 / (1.0 + Math.exp(-x));
    }


    /* public double sigmoidFunction(double value) {
         return (1 / (1 + Math.pow(Math.E, (-1 * value))));
     }
 */
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
            calculateErrorsAndChangeWeights();
            error = (targets[0] - outputWaarden[0]) + (targets[1] - outputWaarden[1]);
            error = Math.abs(error);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            setChanged();
            notifyObservers();
            epoch++;
            progress = ((double) epoch) / maxEpoch;
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

    private void calculateErrorsAndChangeWeights() {
        //calculating outputError
        for (int i = 0; i < outputErrors.length; i++) {
            outputErrors[i] = outputWaarden[i] * (1 - outputWaarden[i]) * (targets[i] - outputWaarden[i]);
        }

        //changeWeights
        for (int hiddenTeller = 0; hiddenTeller < tweedeAxonen.length; hiddenTeller++) {
            for (int outputTeller = 0; outputTeller < outputWaarden.length; outputTeller++) {
                tweedeAxonen[hiddenTeller][outputTeller] = tweedeAxonen[hiddenTeller][outputTeller] + learningRate * outputErrors[outputTeller] * tempHiddens[hiddenTeller];
            }

        }

        //calculating hiddenErrors
        for (int hiddenTeller = 0; hiddenTeller < tempHiddens.length; hiddenTeller++) {
            hiddenErrors[hiddenTeller] = tempHiddens[hiddenTeller] * (1 - tempHiddens[hiddenTeller]) * ((outputErrors[0] * tweedeAxonen[hiddenTeller][0]) + (outputErrors[1] * tweedeAxonen[hiddenTeller][1]));
        }

        //change hidden layer weights
        for (int hiddenTeller = 0; hiddenTeller < tempHiddens.length; hiddenTeller++) {
            for (int inputTeller = 0; inputTeller < inputWaarden.length; inputTeller++) {
                eersteAxonen[inputTeller][hiddenTeller] = eersteAxonen[inputTeller][hiddenTeller] + learningRate * hiddenErrors[hiddenTeller] * inputWaarden[inputTeller];
            }
        }
    }


}
