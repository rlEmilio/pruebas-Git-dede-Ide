package Concesionario;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Concesionario {

    //PROPIEDADES CLASE
    String nombre;
    String direccion;
    //elijo arrayList porque me interesa poder trabajar con los índices de la lista y tener un orden.
    private ArrayList<Vehiculo> vehiculos;

    //no puedo añadir mas propiedades


    //CONSTRUCTOR PARAMETROS
    public Concesionario( String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;

        //ya no necesitamos un tamaño, estamos usando un arrayList
        //comprobarTamano(tam);

        //for para iniciar array en null
        for (Vehiculo v : vehiculos) {
            v = null;
        }
    }


    //CONSTRUCTOR FICHERO EXTERNO
    public Concesionario(String ruta) throws ConcesionarioException {

        BufferedReader lectura = null;

        try {
            lectura = new BufferedReader(new FileReader(ruta));
            this.nombre = lectura.readLine(); //primera linea fichero
            this.direccion = lectura.readLine(); //segunda linea
            /*int tam = Integer.parseInt(lectura.readLine()); //tercera linea
            comprobarTamano(tam);*/

        } catch (FileNotFoundException e) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e) {
            System.out.println("Problema leyendo");
        } finally {
            try {
                if (lectura != null) {
                    lectura.close();
                }
            } catch (IOException e) {
                System.out.println("Problema cerrando flujo");
            }
        }

    }

    //añadir vehiculo
    public boolean anadirVehiculo(Vehiculo nuevo) {

       /* boolean conseguido = false;
        int i = 0;

        while (!conseguido && i < vehiculos.length) {
            if (vehiculos[i] == null) {
                vehiculos[i] = nuevo;
                conseguido = true;
            }
            i++;
        }
        return conseguido;*/

        vehiculos.add(nuevo);
        return true; //con el arrayList siempre podré añadir nuevos vehículos
    }


    //leer lista
    public void leerListaVehiculos(String ruta) {
        BufferedReader lectura = null;

        try {
            lectura = new BufferedReader(new FileReader(ruta));
            String linea = lectura.readLine();
            while (linea != null) {

                //me traigo los datos del fichero
                String[] datos = linea.split(";");
                String marca = datos[0];
                String modelo = datos[1];
                String color = datos[2];
                String matricula = datos[3];
                String esNuevo = datos[4];
                int kmt = Integer.parseInt(datos[5]);
                Vehiculo vehiculoAnadir;

                //creo vehiculos usando constructores diferentes según sea nuevo o no
                boolean nuevo = false;
                if (esNuevo.equalsIgnoreCase("N")) {
                    nuevo = true;
                    try {
                        vehiculoAnadir = new Vehiculo(matricula, marca, modelo, color);
                    }catch (VehiculoException e){
                        System.out.println(e.getMessage());
                    }

                } else {
                    try {
                        vehiculoAnadir = new Vehiculo(matricula, marca, modelo, color,kmt);
                    }catch (VehiculoException e){
                        System.out.println(e.getMessage());
                    }
                }
                //añadir vehiculo al array de vehiculos
               //anadirVehiculo(vehiculoAnadir);

                //actualizo lectura
                linea = lectura.readLine();

            }

        } catch (FileNotFoundException e) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e) {
            System.out.println("Problema leyendo");
        } finally {
            try {
                if (lectura != null) {
                    lectura.close();
                }
            } catch (IOException e) {
                System.out.println("Problema cerrando flujo");
            }
        }

    }


    private int buscarVehiculo(String matricula) {
        boolean encontrado = false;
        int i = 0;
        int pos = -1;
        int tamano = vehiculos.size();

        while (!encontrado && i < tamano) {
            if (vehiculos.get(i).getMatricula().equals(matricula)) {
                pos = i;
                encontrado = true;
            }
            i++;
        }
        return pos;
    }

    public boolean borrarVehiculo(String matricula) {
        boolean borrado = false;
        int i = 0;
        int tamano = vehiculos.size();
        while (!borrado && i < tamano) {
            if (vehiculos.get(i).getMatricula().equals(matricula)) {
                vehiculos.remove(i);
                borrado = true;
            }
            i++;
        }
        return borrado;
    }


    /*private void comprobarTamano(int tam) throws ConcesionarioException{
        if (tam <= 0){
            throw new ConcesionarioException("Tamaño inválido");
        }
        vehiculos = new Vehiculo[tam];


    }*/

    private void pruebaGithub(){
        System.out.println("Esto es una prueba de github");
    }

}
