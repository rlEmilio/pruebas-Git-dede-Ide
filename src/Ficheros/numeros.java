package Ficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class numeros {

    public static void main(String[] args) {


        //voy a guardar una cadena con los numeros para
        //convertirla a array usando split.
        String cadenaNum = "";
        int mayor=0;
        int menor=100;
        int contadorMayor=0;
        int contadorMenor=0;

        String ruta = "src/Ficheros/numeros.txt";
        BufferedReader miBuffer = null;

        try {
            miBuffer = new BufferedReader(new FileReader(ruta));

            String linea = miBuffer.readLine();
            while (linea != null) {
                cadenaNum += linea + " ";
                linea = miBuffer.readLine();

            }

            //System.out.println(cadenaNum);

           String[] arrayCadena = cadenaNum.split(" ");
            int[] arrayNum = new int[arrayCadena.length];

            //for para sacar menor y mayor
            for (int i=0; i<arrayNum.length;i++){
                arrayNum[i]=Integer.parseInt(arrayCadena[i]);
                if (arrayNum[i]<menor){
                    menor=arrayNum[i];
                }
                if (arrayNum[i]>mayor){
                    mayor=arrayNum[i];
                }
            }

            //for para sacar la cantidad de apariciones de menor y mayor
            for (int i=0; i<arrayNum.length;i++){
                if (arrayNum[i]==menor){
                    contadorMenor++;
                }
                if (arrayNum[i]==mayor){
                    contadorMayor++;
                }
            }


            System.out.println("El número menor es: "+menor+"  La cantidad de " +
                    "veces que aparece es: "+contadorMenor+
                    "\nEl número mayor es: " +mayor+"  La cantidad de veces " +
                    "que aparece es: "+contadorMayor);

        } catch (FileNotFoundException e) {
            System.out.println("Ruta incorrecta");
        } catch (IOException e) {
            System.out.println("Problema leyendo");
        } finally {
            try {
                if (miBuffer != null) miBuffer.close();
            } catch (IOException e) {
                System.out.println("Problema al cerrar flujo");
            }
        }
    }
}
