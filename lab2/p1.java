package lab2;

/**
 * Created by airtouchnewmediasl on 4/9/17.
 * <p>
 * Scrieţi un program ce creează şi antrenează o reţea ce trebuie să înveţe funcţia XOR, cu 2 intrări.
 * Arhitectura reţelei: 2 neuroni în stratul de intrare, 2 neuroni în stratul ascuns, 1 neuron în stratul de ieşire.
 * <p>
 * <p>
 * <p>
 * <p>
 * #0      #2
 * -                #4
 * #1      #3
 */

import lab1.*;

import java.util.Random;

public class p1 {
    public static void main(String args[]) {
        final int nrNeuroniIntrare = 2;
        final int nrNeuroniAscunsi = 2;
        final int nrNeuroniIesire = 1;
        final double EROARE_LIMITA = 0.01;
        boolean converged = false;

        double alfa = 1.0;
        double eta = 1.0;

        int nrEpoci = 0;
        double eroareCurenta = 0.0;

        double[] intrare1 = {0, 0, 1, 1};
        double[] intrare2 = {0, 1, 0, 1};
        double[] iesire = {1, 0, 0, 1}; //XOR

        Random r = new Random();
        double w20 = r.nextDouble() - 0.5;
        double w30 = r.nextDouble() - 0.5;
        double w21 = r.nextDouble() - 0.5;
        double w31 = r.nextDouble() - 0.5;
        double w42 = r.nextDouble() - 0.5;
        double w43 = r.nextDouble() - 0.5;

        while (!converged) {

            double[] iesireAscunsa = new double[4];
            for (int i = 0; i < 4; i++) { //i este numarul sablonului, iau la fiecare iteratie une element
                // propagare inainte

                //calculez iesirea ascunsa
                iesireAscunsa[0] = intrare1[i];
                iesireAscunsa[1] = intrare2[i];
                double a = intrare1[i] * w20 + intrare2[i] * w21; // calculez activarea pentru primul element din stratul ascuns
                iesireAscunsa[2] = 1 / (1 + Math.exp(-a));//calculez iesirea pentru primul element din stratul ascuns
                a = intrare1[i] * w30 + intrare2[i] * w31;
                iesireAscunsa[3] = 1 / (1 + Math.exp(-a));

                a = iesireAscunsa[2] * w42 + iesireAscunsa[3] * w43; // calculez activarea pentru iesire
                double iesireCalculata = 1 / (1 + Math.exp(-a));// calculez iesirea
                double iesireDorita = iesire[i];

                //calculez eroarea la pasul curent
                eroareCurenta += (iesireCalculata - iesireDorita) * (iesireCalculata - iesireDorita);

                // propagare inapoi = backpropagation

                // modificare ponderi
                //  Formula de ajustare ponderi: w_ji = alfa * w_ji - eta*out[i]*delta_j
                // Daca j este nod din ultimul strat: delta_j= (yCalculat_j-yDorit_j) * derivata functiei de transfer

                double delta4 = (iesireCalculata - iesireDorita) * (1 - iesireCalculata) * iesireCalculata;
                w43 = alfa * w43 - eta * iesireAscunsa[3] * delta4;
                w42 = alfa * w42 - eta * iesireAscunsa[2] * delta4;

                /*
                delta_j = (suma ponderata delta noduri fii ai lui j)*derivata functiei de transfer in raport cu intrarea totala, calculata in nodul j.
                Pentru functia de transfer sigmoida : f=1/(1+exp(-x)), avem: derivata f'(x)=f(1-f).
                Deci in nodul j, derivata este: out[j]*(1-out[j]) .
                 */

                double delta2 = w42 * delta4 * (1 - iesireAscunsa[2]) * iesireAscunsa[2];
                double delta3 = w43 * delta4 * (1 - iesireAscunsa[3]) * iesireAscunsa[3];

                w20 = alfa * w20 - eta * iesireAscunsa[0] * delta2;
                w21 = alfa * w21 - eta * iesireAscunsa[1] * delta2;
                w30 = alfa * w30 - eta * iesireAscunsa[0] * delta3;
                w31 = alfa * w31 - eta * iesireAscunsa[1] * delta3;

            }

            nrEpoci++;

            System.out.println("Epoca " + nrEpoci + ", eroare curenta " + eroareCurenta);

            if (eroareCurenta <= EROARE_LIMITA)
                converged = true;
        }

        System.out.println("w20=" + w20 + " w21=" + w21);
        System.out.println("w30=" + w30 + " w31=" + w31);
        System.out.println("w42=" + w42 + " w43=" + w43);

    }
}
