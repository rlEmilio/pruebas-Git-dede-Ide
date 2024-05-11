package Ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.management.StringValueExp;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class HorseAlone {

    public static void main(String[] args) {

        //----DATOS POR DEFECTO DE JUGADOR-----
        double dinero = 100;
        int aput = 0, apug = 0;


        //-----PIDO NOMBRE POR TECLADO-----
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido al juego de los caballos, dame tu nombre: ");
        String nombre = teclado.nextLine();


        //-----RUTAS Y BUFFERS------
        String rutaJugador = "src/data/" + nombre + ".dat";
        String rutaCaballos = "src/data/Horses.dat";
        BufferedWriter escritura = null;
        BufferedReader lecturaCaballos = null;
        BufferedReader lecturaJugador = null;
        String cadenaCaballos = "";
        String cadenaJugador = "";


        //cargar datos de jugador
        try {
            File archivo = new File(rutaJugador);
            //---SI EL ARCHIVO EXISTE----
            if (archivo.exists()) {
                //leo y muestro los datos

                try {
                    lecturaJugador = new BufferedReader(new FileReader(rutaJugador));
                    System.out.println();
                    System.out.println("Datos del jugador");
                    String linea = lecturaJugador.readLine();
                    while (linea != null) {
                        System.out.println(linea);
                        cadenaJugador += linea + "\n";
                        linea = lecturaJugador.readLine();
                    }
                    //cargo datos del fichero existente. cada linea del fichero la divido en
                    //dos y guardo los datos del segundo cajon.
                    String[] datosJugador = cadenaJugador.split("\n");
                    String[] sacarDinero = datosJugador[1].split(":");
                    dinero = Double.parseDouble(sacarDinero[1]);
                    String[] sacarApuestasT = datosJugador[2].split(":");
                    aput = Integer.parseInt(sacarApuestasT[1]);
                    String[] sacarApuestasG = datosJugador[3].split(":");
                    apug = Integer.parseInt(sacarApuestasG[1]);


                } catch (IOException e) {
                    System.out.println("Problema leyendo");
                }
            }
            //---SI EL ARCHIVO NO EXISTE----
            else {
                //creo el archivo
                escritura = new BufferedWriter(new FileWriter(rutaJugador));
                //escribo valores por defecto
                escritura.write("Nombre:" + nombre + "\n" +
                        "Dinero:" + dinero + "\nAput:" + aput + "\nApug:" + apug);
                escritura.close(); //si no cierro la escritura me da problemas la lectura


            }
        } catch (FileNotFoundException e) {
            System.out.println("La ruta no es correcta");
        } catch (IOException e) {
            System.out.println("Problema escribiendo o leyendo");
        }

        //cargar datos de caballos
        try {
            File archivo = new File(rutaCaballos);
            if (archivo.exists()) {
                lecturaCaballos = new BufferedReader(new FileReader(rutaCaballos));
                System.out.println();
                System.out.println("Datos de los caballos: ");
                String linea = lecturaCaballos.readLine();
                while (linea != null) {
                    System.out.println(linea);
                    cadenaCaballos += linea + "\n"; //añado salto de linea para tener cada linea por separado
                    linea = lecturaCaballos.readLine();
                }
            } else {
                System.out.println("\nNo hay caballos, Las carreras no pueden realizarse ");
            }
        } catch (IOException e) {
            System.out.println("Problema leyendo");
        } finally {
            try {
                if (lecturaCaballos != null) {
                    lecturaCaballos.close();
                    if (lecturaJugador != null) {
                        lecturaJugador.close();
                    }
                }
            } catch (IOException e) {
                System.out.println("Problema cerrando flujo");
            }
        }


//Mostrar datos de los caballos que compiten
        //quiero tener por un lado los nombres y por otro las victorias

        //mi cadena estaba previamente concatenada con saltos, que ahora uso para dividir
        //y tener las líneas separadas. (cada línea es un hueco del array)
        String[] competidores = cadenaCaballos.split("\n");


        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<String> victorias = new ArrayList<>();

        for (int i = 0; i < competidores.length; i++) {
            //en cada vuelta divido la línea en dos huecos, el primer hueco será un nombre
            //y el segundo una victoria
            String[] partes = competidores[i].split(":");
            nombres.add(partes[0]);
            victorias.add(partes[1]);
        }

        System.out.println("\n---------");

        for (int i = 0; i < competidores.length; i++) {
            System.out.println("Nombre del caballo: " + nombres.get(i) + " | Victorias: " + victorias.get(i));
        }


        //----------------APUESTA---------------
        boolean continuar = true;
        if (dinero < 15) {
            System.out.println("No tienes suficiente dinero para apostar");
            continuar = false;
        }

        while (continuar) {

            String caballoApostar = "";
            double apuesta = 0;

            //pido nombre caballo hasta que introduzca uno correcto
            boolean caballoExiste = false;
            do {
                System.out.println("¿Por que caballo quieres apostar?:");
                caballoApostar = teclado.nextLine();
                if (nombres.contains(caballoApostar)) {
                    caballoExiste = true;
                } else {
                    System.out.println("Ese caballo está jubilado, dime otro");
                }
            } while (!caballoExiste);


            boolean cerrarApuesta = false;
            do {
                //pido apuesta hasta que sea válida y se confirme
                System.out.println("¿Que cantidad quieres apostar?");
                try {
                    apuesta = teclado.nextDouble();
                    if (apuesta < 15) {
                        System.out.println("La apuesta debe ser mayor a 15€");
                    } else if (apuesta > dinero) {
                        System.out.println("No tienes suficiente dinero");
                    } else {
                        System.out.println("confirmar apuesta: S/N");
                        teclado.nextLine();//limpiar buffer
                        String confirmar = teclado.nextLine();
                        if (confirmar.equalsIgnoreCase("s")) {
                            cerrarApuesta = true;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Formato inválido, introduce un valor numérico mayor a 15");
                    teclado.nextLine();//limpio buffer
                }

            } while (!cerrarApuesta);


            // --------------QUIEN GANA------------
            int random = (int) (Math.random() * nombres.size());
            String ganador = nombres.get(random);

            System.out.println("------------");

            String narracion = ("\nAllá van nuestros adorados caballos, haciendo temblar la tierra con cada pisada. \n" +
                    "El número uno lidera la carrera con firmeza... el seis se acerca rápidamente y \n" +
                    "el cuatro está justo detrás, a punto de alcanzarlo...\n" +
                    "quedan pocos metros para la linea de meta, la tensión se palpa en el ambiente y....." +
                    "el ganador es....\n ");


            //le meto unas trompetillas mientras se narra la carrera
            String rutaMusica = "src/Ficheros/trompetas.wav";

            try {
                File file = new File(rutaMusica);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }


            for (int i = 0; i < narracion.length(); i++) {
                System.out.print(narracion.charAt(i));
                try {
                    Thread.sleep(30); //pausa entre letra y letra de 0.03 segundos
                } catch (InterruptedException e) {
                    System.out.println("Problema pausando");
                }
            }
            //Doy mayor pausa a la revelación del ganador (se me va la pinza)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Problema pausando");
            }
            System.out.println(ganador + " !!!!!!!!");


            //-------ACTUALIZO DATOS-------
            int victoriasGanador = Integer.parseInt(victorias.get(random)) + 1;
            victorias.set(random, String.valueOf(victoriasGanador));

            aput += 1;
            if (ganador.equals(caballoApostar)) {
                dinero += apuesta * 1.5;
                apug += 1;
            } else {
                dinero -= apuesta;
            }
            System.out.println("\nEl jugador ahora tiene " + dinero + "€");


            if (dinero > 15) {
                System.out.println("\n--------\n" +
                        "¿Quiere seguir apostando?: S/N");
                String respuesta = teclado.nextLine();
                if (respuesta.equalsIgnoreCase("n")) {
                    continuar = false;
                }
            } else {
                System.out.println("No tienes suficiente dinero");
                continuar = false;
            }
        }

        System.out.println("\nMuchas gracias por jugar a Hol Horse Alone, nos vemos pronto!!!");
        BufferedWriter actualizarDatosJugador = null;
        BufferedWriter actualizarDatosCaballos = null;
        try {
            actualizarDatosJugador = new BufferedWriter(new FileWriter(rutaJugador));
            actualizarDatosJugador.write("Nombre:" + nombre + "\n" +
                    "Dinero:" + dinero + "\nAput:" + aput + "\nApug:" + apug);
            actualizarDatosJugador.close();

            actualizarDatosCaballos = new BufferedWriter(new FileWriter(rutaCaballos));
            int tam = nombres.size();
            for (int i = 0; i < tam; i++) {
                actualizarDatosCaballos.write(nombres.get(i) + ":");
                actualizarDatosCaballos.write(victorias.get(i) + "\n");
            }
            actualizarDatosCaballos.close();

        } catch (IOException e) {
            System.out.println("Problema escribiendo");
        }


    }

}

