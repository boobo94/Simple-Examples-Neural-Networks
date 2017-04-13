package partial;

/**
 * Created by airtouchnewmediasl on 4/9/17.
 */
public class perceptron {
    public static void main(String[] args) {
        double w1 = -0.5;
        double w2 = 0.2;
        double teta = 0.4;
        double alfa = 0.25;

        int[] x1 = {0, 0, 1, 1};
        int[] x2 = {0, 1, 0, 1};
        int[] yd = {0, 0, 0, 1};

        int nrIteratii = 0;

        for (; ; ) {
            boolean stop = true;
            for (int i = 0; i < x1.length; i++) {
                double a = x1[i] * w1 + x2[i] * w2; //calculez activarea
                double y = (a >= teta) ? 1 : 0; // calculez iesirea
                if (y != yd[i]) {
                    stop = false;

                    //aplic regula perceptron
                    w1 += alfa * x1[i] * (yd[i] - y);
                    w2 += alfa * x2[i] * (yd[i] - y);
                    teta += alfa * (-1) * (yd[i] - y);
                }
            }

            nrIteratii++;
            if (stop == true) // daca y = ydorit pentru toate iteratiile
                break;
        }

        System.out.println("Am invatat in " + nrIteratii + " iteratii.");
        System.out.println("w1=" + w1 + ", w2=" + w2 + ", teta=" + teta);
    }

}
