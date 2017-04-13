package lab1;

import java.util.Scanner;

/**
 * Created by Boobo on 3/3/2017.
 */
public class pp1RN2 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Neuroni intrare= ");
        final int NNI = sc.nextInt();//nr. neuroni intrare
        System.out.print("Neuroni strat ascuns= ");
        final int NNH = sc.nextInt();//nr. neuroni strat ascuns
        System.out.print("Neuroni iesire= ");
        final int NNO = sc.nextInt();//nr. neuroni iesire
        double x[] = new double[NNI];
//initializam intrarile cu numere aleatoare,
//in gama (0, 1):
        int i, j;

        for (i = 0; i < NNI; i++) {
            x[i] = Math.random();
            System.out.println("x[" + i + "]=" + x[i]);
        }
//Initializam ponderile cu numere aleatoare
//mici, in gama (-0.5,+0.5):
//Primul strat de ponderi:
        double w1[][] = new double[NNH][NNI];
        for (i = 0; i < NNH; i++)
            for (j = 0; j < NNI; j++) {
                w1[i][j] = Math.random() - 0.5;
                System.out.println("w1[" + i + "][" + j + "]=" + w1[i][j]);
            }

        //Al doilea strat de ponderi:
        double w2[][] = new double[NNO][NNH];
        for (i = 0; i < NNO; i++)
            for (j = 0; j < NNH; j++) {
                w2[i][j] = Math.random() - 0.5;
                System.out.println("w2[" + i + "][" + j + "]=" + w2[i][j]);
            }
//calcul iesirilor neuronilor din stratul ascuns:
        double yH[] = new double[NNH];
        for (i = 0; i < NNH; i++) {
//calcul activare neuron ascuns i:

            double a = 0;
            for (j = 0; j < NNI; j++)
                a = a + x[j] * w1[i][j];
            yH[i] = 1 / (1 + Math.exp(-a));
        }
//calcul iesirilor neuronilor din stratul de //iesire:
        double yO[] = new double[NNO];
        for (i = 0; i < NNO; i++) {
//calcul activare neuron de iesire i:
            double a = 0;
            for (j = 0; j < NNH; j++)
                a = a + yH[j] * w2[i][j];
            yO[i] = 1 / (1 + Math.exp(-a));
            System.out.println("yO[" + i + "]=" + yO[i]);
        }
    }
}
