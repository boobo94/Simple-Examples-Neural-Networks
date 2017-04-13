package lab2;

/**
 * Created by Boobo on 3/16/2017.
 */
import java.awt.event.*;
import java.util.*;
public abstract class RN1 implements KeyListener {

    static boolean converged;
    /*
     XOR
     Notatiile neuronilor din reteaua 2-2-1, sunt:

        #0 #2
                #4
        #1 #3
    */

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println( "key character");

        }

    }

    public static void main(String args[]) {
        double eta = 1.0;
        double alfa = 1.0;
        double eroareCrtToateSabloanele;
        double eroareMaxToateSabloanele;
        double w20, w30, w21, w31, w42, w43;
        //w20 este ponderea conexiunii intre neuronii 0 si 2
        double out[] = new double[4];
        double yCalculat, yDorit;
        double sablonIn_0[] = {0.1, 0.1, 0.9, 0.9};//sabloanele de antrenare
        //pt. neuron 0.
        double sablonIn_1[] = {0.1, 0.9, 0.1, 0.9};
        //double sablonOut[] = {0.1, 0.9, 0.9, 0.1};//pentru functia XOR
        double sablonOut[] = {0.1, 0.9, 0.9, 0.9};//pentru functia OR
        double delta2, delta3, delta4;
        //delta2=semnalul delta calculat pentru neuronul 2
        double eroareCrtUnSablon[] = new double[4];
        int nrCrtEpoci;
        self:converged = false;
        int i, c;
//        final double EROARE_LIMITA = 0.01;
        Scanner sc = new Scanner(System.in);
        System.out.print("EROARE_LIMITA= ");
        final double EROARE_LIMITA = sc.nextDouble();
        Random r = new Random();
        w20 = r.nextDouble() - 0.5;
        w30 = r.nextDouble() - 0.5;
        w21 = r.nextDouble() - 0.5;
        w31 = r.nextDouble() - 0.5;
        w42 = r.nextDouble() - 0.5;
        w43 = r.nextDouble() - 0.5;
        nrCrtEpoci = 0;
        //Algoritmul de backpropagation:


        while (!converged) {
            for (i = 0; i < 4; i++) {//i-este nr. sablonului
                //propagare inainte:
                out[0] = sablonIn_0[i];
                out[1] = sablonIn_1[i];
                out[2] = 1 / (1 + Math.exp(-w20 * out[0] - w21 * out[1]));
                out[3] = 1 / (1 + Math.exp(-w30 * out[0] - w31 * out[1]));
                yDorit = sablonOut[i];
                yCalculat = 1 / (1 + Math.exp(-w42 * out[2] - w43 * out[3]));
                eroareCrtUnSablon[i] = 0.5 * (yCalculat - yDorit) * (yCalculat);
                //modifica ponderi:
                //Formula de ajustare ponderi: w_ji=alfa*w_ji - eta*out[i]*delta_j
                //Daca j este nod din ultimul strat:
                //delta_j=(yCalculat_j-yDorit_j)*derivata functiei de transfer
                delta4 = (yCalculat - yDorit) * (1 - yCalculat) * yCalculat;
                w42 = alfa * w42 - eta * out[2] * delta4;
                w43 = alfa * w43 - eta * out[3] * delta4;
                /*
                Daca j este nod din strat ascuns:
                delta_j = (suma ponderata delta noduri fii ai lui j)*derivata
                functiei de transfer in raport cu intrarea totala, calculata in nodul j.
                Pentru functia de transfer sigmoida :
                f=1/(1+exp(-x)), avem: derivata f'(x)=f(1-f).
                Deci in nodul j, derivata este: out[j]*(1-out[j]) .
                */
                delta2 = w42 * delta4 * (1 - out[2]) * out[2];
                delta3 = w43 * delta4 * (1 - out[3]) * out[3];
                w20 = alfa * w20 - eta * out[0] * delta2;
                w30 = alfa * w30 - eta * out[0] * delta3;
                w21 = alfa * w21 - eta * out[1] * delta2;
                w31 = alfa * w31 - eta * out[1] * delta3;
            }//for
            nrCrtEpoci++;
            eroareCrtToateSabloanele = 0;
            for (i = 0; i < 4; i++)
                eroareCrtToateSabloanele += eroareCrtUnSablon[i];
            System.out.println("Epoca: " + nrCrtEpoci + ". Eroarea totala: "
                    + eroareCrtToateSabloanele);

            if (eroareCrtToateSabloanele <= EROARE_LIMITA)
                converged = true;
        }//while
        //afisare ponderi:
        System.out.println("w20=" + w20 + " w21=" + w21);
        System.out.println("w30=" + w30 + " w31=" + w31);
        System.out.println("w42=" + w42 + " w43=" + w43);
    }//main

}
