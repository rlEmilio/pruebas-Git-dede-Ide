package Ficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;


import java.util.Scanner;

public class Ejercicio120 {


        public static void main(String[] args) {


            Scanner teclado = new Scanner(System.in);
            System.out.println("Dame el nombre de los dos ficheros: ");
            System.out.print("Fichero 1: ");
            String fichero1 = teclado.nextLine();
            System.out.print("Fichero 2: ");
            String fichero2 = teclado.nextLine();

            //buffers para leer y escribir
            BufferedWriter escritura = null;
            BufferedReader lectura = null;
            //creo la ruta del nuevo archivo, quitando las extensiones de los ficheros con substring
            //concateno con guion y la extension .txt al final.
            String rutaEscritura = "src/Ficheros/"+fichero1.substring(0,fichero1.length()-4)
                    +"-"+fichero2.substring(0,fichero2.length()-4)+".txt";

            //ruta de lectura del primer fichero
            String rutaFichero1 ="src/Ficheros/"+fichero1;
            //ruta de lectura del segundo fichero
            String rutaFichero2 ="src/Ficheros/"+fichero2;

            //variable para leer líneas de los ficheros
            String linea="";


            try {

                //abro buffer de escritura con la ruta del nuevo archivo (el archivo se creará automáticamente)
                escritura = new BufferedWriter(new FileWriter(rutaEscritura));
                //abro buffer de lectura con la ruta del primer fichero
                lectura = new BufferedReader(new FileReader(rutaFichero1));

                while ((linea = lectura.readLine()) != null){
                    escritura.write(linea+"\n");
                }

                //cierro lectura del primer archivo y abro lectura para el segundo.
                lectura.close();
                lectura = new BufferedReader(new FileReader(rutaFichero2));

                while ((linea = lectura.readLine()) != null){
                    escritura.write(linea+"\n");
                }

                System.out.println("Se ha creado el archivo: "+rutaEscritura);

            } catch (FileNotFoundException e) {
                System.out.println("Ruta incorrecta");
            } catch (IOException e) {
                System.out.println("Problema leyendo");
            } finally {
                try {
                    if (escritura != null) escritura.close();
                    if (lectura != null) lectura.close();
                } catch (IOException e) {
                    System.out.println("Problema al cerrar flujo");
                }
            }
        }
    }



