package Ficheros;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio113 {

    public static void main(String[] args) {

        String ruta = "src/Ficheros/EJ113.dat";
        BufferedWriter miBuffer = null;
        Scanner teclado = new Scanner(System.in);

                try {
                    miBuffer = new BufferedWriter(new FileWriter(ruta));

                    String frase="hola";
                    while (!frase.isEmpty()){
                        System.out.println("Escriba una frase: ");
                        frase=(teclado.nextLine());
                        miBuffer.write(frase+"\n");
                    }



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



