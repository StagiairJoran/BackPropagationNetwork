package model;


/**
 * Created by Joachim De Schryver & Joran De Boever
 * on 17/11/15.
 */
public class NeuraalNetwerk {
    private double[] inputWaarden = new double[3];

    private double[][] eersteAxonen = new double[3][4]; // input-to-hidden

    private double[] hiddenWaarden = new double[4];
    private double[] hiddenBiasWaarden = new double[4];

    private double[][] tweedeAxonen = new double[4][2]; // hidden-to-output

    private double[] outputWaarden = new double[2];
    private double[] outputBiasWaarden = new double[2];

    private double[] targets = new double[2];

    public NeuraalNetwerk() {
        init();
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

}
