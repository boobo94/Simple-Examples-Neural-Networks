package lab1;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Boobo on 3/3/2017.
 */
public class pp2RN2 {

    public static int[] readFromFile() {
        System.out.println("Reading File from Java code");
        //Name of the file
        String fileName = "E:\\Facultate\\Anul IV\\Semestrul II\\Retele neuronale\\Laborator\\src\\lab1\\test2.txt";
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

        final int NNI = v[0];//nr. neuroni intrare
        final int NNH = v[1];//nr. neuroni strat ascuns
        final int NNO = v[2];//nr. neuroni iesire
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
