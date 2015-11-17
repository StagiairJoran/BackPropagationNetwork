/**
 * Created by Joachim De Schryver & Joran De Boever
 * on 17/11/15.
 */
public class NeuraalNetwerk {
    private double[] inputWaarden;

    private double[][] eersteAxonen;

    private double[] hiddenWaarden;
    private double[] hiddenBiasWaarden;

    private double[][] tweedeAxonen;

    private double[] outputWaarden;
    private double[] outputBiasWaarden;


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
}
