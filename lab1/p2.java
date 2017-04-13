package lab1;

/**
 * Created by airtouchnewmediasl on 4/9/17.
 * <p>
 * Scriem un program care, pentru o reţea neuronală ce are un număr oarecare de neuroni în stratul de intrare,
 * un număr oarecare de neuroni în stratul ascuns şi un număr oarecare de neuroni în stratul de ieşire,
 * calculează ieşirea reţelei ( ilustrează etapa de propagare înainte din algoritmul de backpropagation).
 */
public class p2 {

    public static double[] generateRandomVector(int columns) {
        double randomArray[] = new double[columns];

        for (int i = 0; i < randomArray.length; i++)
            randomArray[i] = Math.random(); // generez numere aleatoare in intervalul [0,1]

        return randomArray;
    }

    public static void main(String[] args) {
        final int nrNeuroniIntrare = 2;
        final int nrNeuroniAscunsi = 3;
        final int nrNeuroniIesire = 2;

        //initializare valori in stratul de intrare
        double arrayIntrari[] = generateRandomVector(nrNeuroniIntrare); // generez un vector cu numere aleatoare pentru a popula stratul de intrare

        p1 exercise1 = new p1(); // use methods from class p1 to generate random weight;

        double w1[][] = exercise1.generateWeight(nrNeuroniAscunsi, nrNeuroniIntrare); // populez matricea de ponderi cu valori aleatoare in intervalul [-0.5,0.5]
        double w2[][] = exercise1.generateWeight(nrNeuroniIesire, nrNeuroniAscunsi);

        // calculez iesirile din stratul ascuns
        double iesiriAscunse[] = new double[nrNeuroniAscunsi];

        for (int i = 0; i < nrNeuroniAscunsi; i++) {
            double a = 0;
            for (int j = 0; j < nrNeuroniIntrare; j++) {
                a += arrayIntrari[j] * w1[i][j];
            }
            iesiriAscunse[i] = 1 / (1 + Math.exp(-a));
        }

        //calculez iesirile din stratul de iesire
        double iesiri[] = new double[nrNeuroniIesire];

        for (int i = 0; i < nrNeuroniIesire; i++) {
            double a = 0;
            for (int j = 0; j < nrNeuroniAscunsi; j++) {
                a += iesiriAscunse[j] * w2[i][j];
            }

            iesiri[i] = 1 / (1 + Math.exp(-a));
            System.out.println("Iesirea " + i + " este " + iesiri[i]);
        }
    }
}
