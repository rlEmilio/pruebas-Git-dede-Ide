package Ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class plantilla {

    public static void main(String[] args) {



        String ruta = "src/Ficheros/numeros.txt";
        BufferedReader miBuffer = null;


        try {
            miBuffer = new BufferedReader(new FileReader(ruta));



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
