package Ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio114 {
    public static void main(String[] args) {

        int contador=0;
        String ruta="src/Ficheros/Ejercicio114.txt";
       BufferedReader miBuffer =null;


        try {
            miBuffer = new BufferedReader(new FileReader(ruta));

            String linea = miBuffer.readLine();
            while (linea != null){
                contador++;
                System.out.println(contador+":"+linea);
                linea=miBuffer.readLine();

            }

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
