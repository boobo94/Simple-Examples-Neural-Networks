package lab1;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by airtouchnewmediasl on 4/9/17.
 * Scriem un program care, pentru o reţea neuronală ce are un număr oarecare de neuroni în stratul de intrare,
 * un număr oarecare de neuroni în stratul ascuns şi un singur neuron în stratul de ieşire, calculează ieşirea reţelei
 * ( ilustrează etapa de propagare înainte din algoritmul de backpropagation).
 */
public class p1 {

    /**
     * generate a vector
     *
     * @param columns - number of columns
     * @return {vector}
     */
    public static double[] generateWeight(int columns) {
        double arrayWeight[] = new double[columns];
        Random r = new Random();

        for (int j = 0; j < arrayWeight.length; j++) {
            arrayWeight[j] = r.nextDouble() - 0.5; // generate values beteween -0.5 and 0.5
        }

        return arrayWeight;
    }

    /**
     * generate an array
     *
     * @param rows    - number of rows
     * @param columns - number of rows
     * @return {array}
     */
    public static double[][] generateWeight(int rows, int columns) {
        double arrayWeight[][] = new double[rows][columns];

        Random r = new Random();

        for (int i = 0; i < arrayWeight.length; i++)
            for (int j = 0; j < arrayWeight[0].length; j++) {
                arrayWeight[i][j] = r.nextDouble() - 0.5;
            }

        return arrayWeight;
    }

    public static void main(String[] args) {
        final int nrNeuroniIntrare = 2;// numar neuroni de intrare
        final int nrNeuroniAscunsi = 3; // numar neuroni din stratul ascuns
        double arrIntrari[] = new double[nrNeuroniIntrare];

        /*

                w1            w2
        *               #3
        *   #1
        *               #4          #6
        *   #2
        *               #5
         */

        //Atribuim valori oarecare inrarilor (date de test a //aplicatiei):
        arrIntrari[0] = 0.7;
        arrIntrari[1] = 0.25;

        double w1[][] = generateWeight(nrNeuroniAscunsi, nrNeuroniIntrare); // initializare ponderi primul strat
        double w2[] = generateWeight(nrNeuroniAscunsi); // initalizare ponderi pentru stratul de iesire, folosim un vector deoarece avem un singur neuron de iesire

        //calculam iesirile neuronilor din stratul ascuns
        double iesiriStratAscuns[] = new double[nrNeuroniAscunsi];

        for (int i = 0; i < nrNeuroniAscunsi; i++) {
            double a = 0;

            for (int j = 0; j < nrNeuroniIntrare; j++)
                a += arrIntrari[j] * w1[i][j];

            iesiriStratAscuns[i] = 1 / (1 + Math.exp(-a));
        }

        // calcul iesire

        double a = 0;
        for (int i = 0; i < nrNeuroniAscunsi; i++)
            a += iesiriStratAscuns[i] * w2[i]; // calculez activarea pentru iesire

        double iesire = 1 / (1 + Math.exp(-a));

        System.out.println("Iesirea este "+iesire);

    }
}
