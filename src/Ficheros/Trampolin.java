package Ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;


public class Trampolin {

    public static void main(String[] args) {

        double suma=0;
        String ruta = "src/Ficheros/Trampolin.txt";
        BufferedReader miBuffer = null;

        try {

            miBuffer = new BufferedReader(new FileReader(ruta));

            //guardo las notas en un array de string y luego lo convierto a double
            String[] notas = miBuffer.readLine().split(" ");
            double[] notasNum = new double[notas.length];

            for (int i = 0; i < notas.length; i++) {
                notasNum[i] = Double.parseDouble(notas[i]);
            }

            //metodo burbuja para ordenar de menor a mayor.
           /* for (int i = 0; i < notasNum.length - 1; i++) {
                for (int j = 0; j < notasNum.length - 1; j++) {
                    if (notasNum[j] > notasNum[j + 1]) {
                        double temp = notasNum[j];
                        notasNum[j] = notasNum[j + 1];
                        notasNum[j + 1] = temp;
                    }
                }
            }*/

            //arrays sort para ordenar de menor a mayor
            Arrays.sort(notasNum);
            double cd = Double.parseDouble(miBuffer.readLine());

            for (int i = 2; i < notas.length - 2; i++) {
               suma=suma+notasNum[i];
            }

            System.out.println("La nota final obtenida es: "+suma/cd);

        } catch (FileNotFoundException e) {
            System.out.println("Ruta incorrecta");
        } catch (IOException e) {
            System.out.println("Problema leyendo");
        } finally {
            try {
                if (miBuffer != null) miBuffer.close();

            } catch (IOException e) {
                System.out.println("El buffer no ha podido cerrarse");
            }
        }


    }
}
