package Ficheros;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NumeroLetras {

    public static void main(String[] args) {


        String ruta = "src/Ficheros/NumeroLetras";
        BufferedReader miBuffer = null;

        try {

            miBuffer = new BufferedReader(new FileReader(ruta));



        }
        catch (FileNotFoundException e){
            System.out.println("Ruta incorrecta, peinate, dice enya");
        }
        catch (IOException e){
            System.out.println("Problema leyendo");
        }
        finally {
            try{
                if (miBuffer != null) miBuffer.close();



            }catch (IOException e){
                System.out.println("El buffer no ha podido cerrarse");
            }
        }



    }
}
