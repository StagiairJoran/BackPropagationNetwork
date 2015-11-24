package model;


import java.util.Observable;
import java.util.Random;

/**
 * Created by Joachim De Schryver & Joran De Boever
 * on 17/11/15.
 */
public class NeuraalNetwerk extends Observable {

    private double[] inputWaarden = new double[3];

    private double[][] eersteAxonen = new double[3][4]; // input-to-hidden

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

    private int maxEpoch = 10000;

    private double error;

    private double errorThreshold = 0.0001;

    private double momentum;

    private double learningRate = 0.5;

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

    public double sigmoidFunction(double value) {
        return (1 / (1 + Math.pow(Math.E, (-1 * value))));
    }

    public void startBackPropagation() {

        calculateNeurons();
        calculateErrorsAndChangeWeights();

        setChanged();
        notifyObservers();

            /*
            for(int i = 0; i<10;i++) {
            eersteAxonen[0][0] = i;
            eersteAxonen[0][1] = i+1;
            eersteAxonen[0][2] = i+3;
            eersteAxonen[0][3] = i+5;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            */
    }

    private void calculateNeurons() {
        //calculating hidden neurons
        for (int hiddenTeller = 0; hiddenTeller < hiddenWaarden.length; hiddenTeller++) {
            Double neuronTemp = 0.00;
            for (int inputTeller = 0; inputTeller < inputWaarden.length; inputTeller++) {
                neuronTemp += inputWaarden[inputTeller] * eersteAxonen[inputTeller][hiddenTeller];
            }
            hiddenWaarden[hiddenTeller] = sigmoidFunction(neuronTemp);
        }

        //calculating output neurons
        for (int outputTeller = 0; outputTeller < outputWaarden.length; outputTeller++) {
            Double neuronTemp = 0.00;
            for (int hiddenTeller = 0; hiddenTeller < hiddenWaarden.length; hiddenTeller++) {
                neuronTemp += hiddenWaarden[hiddenTeller] * tweedeAxonen[hiddenTeller][outputTeller];
            }
            outputWaarden[outputTeller] = sigmoidFunction(neuronTemp);
        }
    }

    private void calculateErrorsAndChangeWeights(){
        //calculating outputError
        for(int i = 0;i<outputErrors.length;i++){
            outputErrors[i] = outputWaarden[i]*(1-outputWaarden[i])*(targets[i]-outputWaarden[i]);
        }

        //changeWeights


        //calculating hiddenErrors
        for(int i = 0; i<hiddenErrors.length;i++){
            hiddenErrors[i] = outputWaarden[i]*(1-outputWaarden[i])*(outputErrors[0]*-outputWaarden[i]);
        }
    }


}
