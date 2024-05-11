package Ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Ejercicio116 {

    public static void main(String[] args) {

        String ruta = "src/Ficheros/Ejercicio116.txt";
        BufferedReader miBuffer = null;
        String cadena = "";
        String mayor = "";


        try {
            miBuffer = new BufferedReader(new FileReader(ruta));

            //para guardar las frases del fichero voy a usar una cadena
            //a esta cadena le añado saltos que luego usaré para dividirla en trozos(array).
            String linea = miBuffer.readLine();
            while (linea != null) {
                cadena += linea + "\n";
                linea = miBuffer.readLine();
            }

            //creo el array separando por saltos
            String[] arrayCadena = cadena.split("\n");

            //una vez creado el array de frases, puedo recorrerlo para ver las más largas.
            //este for muestra la primera frase larga.
            for (int i = 0; i < arrayCadena.length; i++) {
                if (arrayCadena[i].length() > mayor.length()) {
                    mayor = arrayCadena[i];
                }
            }
            //System.out.println(mayor);

            //este for muestra la última frase larga.
            for (int i = 0; i < arrayCadena.length; i++) {
                if (arrayCadena[i].length() == mayor.length()) {
                    mayor = arrayCadena[i];
                }
            }
            System.out.println(mayor);

            //este for muestra todas las frases largas.
            for (int i = 0; i < arrayCadena.length; i++) {
                if (arrayCadena[i].length() == mayor.length()) {
                    mayor = arrayCadena[i];
                    //System.out.println(mayor);
                }
            }
            //en esos bucles cambia la condición del if y la posicion del sout


        } catch (FileNotFoundException e) {
            System.out.println("Ruta incorrecta");
        } catch (IOException e) {
            System.out.println("Problema escribiendo");
        } finally {
            try {
                if (miBuffer != null) miBuffer.close();
            } catch (IOException e) {
                System.out.println("Problema al cerrar flujo");
            }
        }
    }
}






