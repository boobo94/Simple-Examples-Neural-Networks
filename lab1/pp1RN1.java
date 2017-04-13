package lab1;
import java.util.Scanner;

/**
 * Created by Boobo on 3/3/2017.
 */
public class pp1RN1 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nr neuroni intrare= ");
        final int NNI = sc.nextInt();//Numarul de neuroni de intrare
        System.out.print("Nr neuroni iesire= ");
        final int NNH = sc.nextInt();//Numarul de neuroni de iesire

        //Intrarile, le memoram in vactorul x[]:
        double x[] = new double[NNI];

        //Atribuim valori oarecare inrarilor (date de test a aplicatiei):

        System.out.println("*** Neuroni de intrare ***");

        for(int i=0;i<NNI;i++) {
            System.out.print("x["+i+"]= ");
            x[i] = sc.nextInt();
            System.out.println();
        }

        //Ponderile, primul strat, le memoram in matricea
        //w1[][]:
        double w1[][] = new double[NNH][NNI];
        //Atribuim valori oarecare acestori ponderi (date de test ):

        System.out.println("Ponderi pentru primul strat");

        for(int i=0;i<NNH;i++)
            for(int j=0;j<NNI;j++) {
                System.out.print("x["+i+"]["+j+"]= ");
                w1[i][j] = sc.nextInt();
                System.out.println();
            }

        //Ponderile, al doilea strat, le memoram in vectorul w2[]. (folosim un vector si nu o matrice, pentru ca
        //avem un singur neuron de iesire).
        double w2[] = new double[NNH];

        System.out.println("Ponderi pentru al doilea strat");

        for(int i=0;i<NNH;i++) {
            System.out.print("w2["+i+"]= ");
            w2[i] = sc.nextInt();
            System.out.println();
        }


        //calcul iesiri neuroni din stratul ascuns:
        double yH[] = new double[NNH];
        for (int i = 0; i < NNH; i++) {

            //calcul activare neuron ascuns i:
            double a = 0;
            for (int j = 0; j < NNI; j++)
                a = a + x[j] * w1[i][j];
            yH[i] = 1 / (1 + Math.exp(-a));
        }

        //calcul iesire y:
        double a = 0;
        for (int i = 0; i < NNH; i++)
            a = a + yH[i] * w2[i];
        double y = 1 / (1 + Math.exp(-a));

        System.out.println("y=" + y);
    }
}
