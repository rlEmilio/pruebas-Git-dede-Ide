package Ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejericicio117 {

    public static void main(String[] args) {


        String ruta = "src/Ficheros/Ejercicio117.txt";
        BufferedReader miBuffer = null;
        String cadena = "";
        int contadorLineas = 0;

        try {
            miBuffer = new BufferedReader(new FileReader(ruta));


            String linea = miBuffer.readLine();
            while (linea != null) {
                contadorLineas++;
                cadena += linea;
                linea = miBuffer.readLine();
            }
            System.out.println("El número de lineas es: "+contadorLineas);

            String[] palabras = cadena.split(" ");
            System.out.println("El número de palabras es: "+palabras.length);
            System.out.println("El número de carácteres es: "+cadena.length());





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




