package lab1;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Boobo on 3/3/2017.
 */
public class pp2RN1 {

    public static int[] readFromFile() {
        System.out.println("Reading File from Java code");
        //Name of the file
        String fileName = "E:\\Facultate\\Anul IV\\Semestrul II\\Retele neuronale\\Laborator\\src\\lab1\\test.txt";
        int[] v = new int[100];
        try{

            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            //Variable to hold the one line data
            String line;

            int i=0;

            // Read file line by line and print on the console
            while ((line = bufferReader.readLine()) != null)   {
                v[i] = Integer.parseInt(line);
                i++;
            }
            //Close the buffer reader
            bufferReader.close();
        }catch(Exception e){
            System.out.println("Error while reading file line by line:" + e.getMessage());
        }

        return v;
    }

    public static void main(String args[]) {

        int v[] = readFromFile();

        final int NNI = v[0];//Numarul de neuroni de intrare
        final int NNH = v[1];//Numarul de neuroni de iesire

        //Intrarile, le memoram in vactorul x[]:
        double x[] = new double[NNI];

        //Atribuim valori oarecare inrarilor (date de test a aplicatiei):

        x[0] = 0.7;
        x[1] = 0.25;

        //Ponderile, primul strat, le memoram in matricea
        //w1[][]:
        double w1[][] = new double[NNH][NNI];
        //Atribuim valori oarecare acestori ponderi (date de test ):
        w1[0][0] = 0.5;
        w1[0][1] = -0.3;

        w1[1][0] = 0.4;
        w1[1][1] = 0.3;

        w1[2][0] = 0.7;
        w1[2][1] = -0.6;

        //Ponderile, al doilea strat, le memoram in vectorul w2[]. (folosim un vector si nu o matrice, pentru ca
        //avem un singur neuron de iesire).
        double w2[] = new double[NNH];

        //Atribuim valori oarecare acestori ponderi:
        w2[0] = 1.5;
        w2[1] = 2.3;
        w2[2] = 0.4;

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
