package Ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Enya116 {

    public static void main(String[] args) {


        String ruta = "src/Ficheros/Ejercicio116.txt"; //tu ruta
        BufferedReader miBuffer = null;
        String fraseActual = "";
        String fraseMayorP = ""; //primera frase mayor
        String fraseMayorU = ""; //última frase mayor

//Hola eniuchi ^_^

        try {
            miBuffer = new BufferedReader(new FileReader(ruta)); //abro buffer

            while ((fraseActual = miBuffer.readLine()) != null) { //leo texto
                //A -- primera frase larga
                if (fraseActual.length() > fraseMayorP.length()) {
                    fraseMayorP = fraseActual;
                }
                //B -- ultima frase larga
                if (fraseActual.length() >= fraseMayorU.length()) {
                    fraseMayorU = fraseActual;
                }
            }
            System.out.println("Primera frase larga: " + fraseMayorP);
            System.out.println("Ultima frase larga: " + fraseMayorU);

            //C -- todas las frases largas
            //cierro y vuelvo a abrir buffer
            miBuffer.close();
            miBuffer = new BufferedReader(new FileReader(ruta));

            System.out.println("Todas las frases largas: ");
            //vuelvo a recorrer el texto, esta vez comparando
            //todas las frases con una de las mayores que ya teniamos
            // (primera o ultima), si son del mismo tamaño las mostramos dentro del bucle.
            while ((fraseActual = miBuffer.readLine()) != null) {
                if (fraseActual.length() == fraseMayorP.length()) {
                    System.out.println(fraseActual);
                }
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



