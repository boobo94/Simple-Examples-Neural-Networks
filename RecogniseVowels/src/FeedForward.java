
import java.util.Random;
import java.util.Scanner;

public class FeedForward {
    public int InputNodes;
    public int HiddenNodes;
    public int OutputNodes;
    public int TemplatesNumber;
    public int Dimension;
    public double[][] WeightsIH;
    public double[][] WeightsHO;
    final double DefaultError = 0.01;
    final int MaximumAges = 40000;
    public int[][] InputTemplates;
    public int[][] OutputTemplates;

    public FeedForward(int hiddenNodes, int outputNodes, int templatesNumber, int dimension) {
        // init number of nodes
        this.InputNodes = dimension * dimension;
        this.HiddenNodes = hiddenNodes;
        this.OutputNodes = outputNodes;
        this.TemplatesNumber = templatesNumber;
        this.Dimension = dimension;

        // declare weights
        this.WeightsIH = new double[hiddenNodes][this.InputNodes];
        this.WeightsHO = new double[outputNodes][hiddenNodes];

        this.initWeights();
    }

    private void initWeights() {
        int i, j;
        Random r = new Random();

        // initialize the input - hidden weights
        for (i = 0; i < this.HiddenNodes; i++)
            for (j = 0; j < this.InputNodes; j++)
                this.WeightsIH[i][j] = r.nextDouble() - 0.5;

        // initialize the hidden - output weights
        for (i = 0; i < this.OutputNodes; i++)
            for (j = 0; j < this.HiddenNodes; j++)
                this.WeightsHO[i][j] = r.nextDouble() - 0.5;

    }

    public boolean Train(int[][] inputs, int[][] outputs) {
        this.InputTemplates = inputs;
        this.OutputTemplates = outputs;

        double yHidden[] = new double[this.HiddenNodes];
        double yOut[] = new double[this.OutputNodes];
        double currentErrors[] = new double[this.TemplatesNumber];
        double TotalError;
        int ages = 0;
        boolean converge = false;
        boolean stop = false;
        int i, ex;

        while (!stop) {

            for (ex = 0; ex < this.TemplatesNumber; ex++) {
                CalculusOutput(yHidden, yOut, ex);
                currentErrors[ex] = 0;
                for (i = 0; i < this.OutputNodes; i++)
                    currentErrors[ex] += 0.5 * (yOut[i] - OutputTemplates[ex][i]) * (yOut[i] - OutputTemplates[ex][i]);
                ChangeWeights(yOut, yHidden, ex);
            }

            ages++;

            TotalError = 0;
            for (ex = 0; ex < this.TemplatesNumber; ex++)
                TotalError += currentErrors[ex];

            System.out.println("Epoca nr: " + ages + " eroare totala= " + TotalError);

            // the network converged
            if (TotalError <= this.DefaultError) {
                converge = true;
                stop = true;
            }

            // the network didn't converged in time
            if (ages > this.MaximumAges)
                stop = true;
        }

        return converge;
    }

    private void ChangeWeights(double yOut[], double yHidden[], int ex) {
        double deltaOut[] = new double[this.OutputNodes];
        double deltaHidden[] = new double[this.HiddenNodes];
        int i, j;
        double weightedSum;
        double alfa = 1.0;
        double eta = 1.0;

        for (i = 0; i < this.OutputNodes; i++)
            deltaOut[i] = (yOut[i] - OutputTemplates[ex][i]) * (1 - yOut[i]) * yOut[i];

        for (i = 0; i < this.OutputNodes; i++)
            for (j = 0; j < this.HiddenNodes; j++)
                this.WeightsHO[i][j] = alfa * this.WeightsHO[i][j] - eta * deltaOut[i] * yHidden[j];

        for (i = 0; i < this.HiddenNodes; i++) {
            weightedSum = 0;
            for (j = 0; j < this.OutputNodes; j++)
                weightedSum += this.WeightsHO[j][i] * deltaOut[j];
            deltaHidden[i] = weightedSum * (1 - yHidden[i]) * yHidden[i];
        }

        for (i = 0; i < this.HiddenNodes; i++)
            for (j = 0; j < this.InputNodes; j++)
                this.WeightsIH[i][j] = alfa * this.WeightsIH[i][j] - eta * deltaHidden[i] * InputTemplates[ex][j];
    }

    private void CalculusOutput(double yHidden[], double yOut[], int ex) {
        int i, j;
        double bias;

        // calculate output for hidden nodes
        for (i = 0; i < this.HiddenNodes; i++) {
            bias = 0;
            for (j = 0; j < this.InputNodes; j++)
                bias += this.InputTemplates[ex][j] * this.WeightsIH[i][j];
            yHidden[i] = this.sigmoid(bias);
        }

        // calculate output for output nodes
        for (i = 0; i < this.OutputNodes; i++) {
            bias = 0;
            for (j = 0; j < this.HiddenNodes; j++)
                bias += yHidden[j] * this.WeightsHO[i][j];
            yOut[i] = this.sigmoid(bias);
        }
    }

    private double sigmoid(double bias) {
        return 1 / (1 + Math.exp(-bias));
    }

    public double[] Check(int[] testData) {
        int i, j;
        double yHidden[] = new double[this.HiddenNodes];
        double yOut[] = new double[this.OutputNodes];
//        int inTest[] = new int[this.InputNodes];
        double bias;

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Dati cele " + this.InputNodes + " valori binare pe intrari: ");
//        for (i = 0; i < this.InputNodes; i++) {
//            if (i % this.Dimension == 0)
//                System.out.println("\n\n");
//            System.out.println("Valoarea pixelului " + i + ":");
//            inTest[i] = sc.nextInt();
//        }

        for (i = 0; i < this.HiddenNodes; i++) {
            bias = 0;
            for (j = 0; j < this.InputNodes; j++) {
                bias = bias + testData[j] * this.WeightsIH[i][j];
            }
            yHidden[i] = this.sigmoid(bias);
        }

        for (i = 0; i < this.OutputNodes; i++) {
            bias = 0;
            for (j = 0; j < this.HiddenNodes; j++) {
                bias = bias + yHidden[j] * this.WeightsHO[i][j];
            }
            yOut[i] = this.sigmoid(bias);
        }

        System.out.print("Results { ");
        for (i = 0; i < yOut.length; i++) {
            System.out.print(yOut[i] + ", ");
        }
        System.out.print(" }");

        return yOut;
    }

}
