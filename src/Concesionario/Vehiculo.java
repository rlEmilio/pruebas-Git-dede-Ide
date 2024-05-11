package Concesionario;




public class Vehiculo {


    //PROPIEDADES CLASE
    private String matricula, marca, modelo, color;
    private boolean nuevo;
    private int kilometros; //si el coche es nuevo tiene los kilometros a 0
    private final String[] colores = {"Amarillo", "Rojo", "Verde", "Azul", "Negro", "Blanco", "Gris"};


    //CONSTRUCTOR PARA COCHE NUEVO
    public Vehiculo(String matricula, String marca, String modelo, String color) throws VehiculoException {
        //metodo privado para comprobar matricula
        comprobarMatricula(matricula);
        this.marca = marca;
        this.modelo = modelo;
        //metodo privado para comprobar si el color es válido
        comprobarColor(color);

        this.nuevo = true; //el coche es nuevo
        this.kilometros = 0; //no tiene kilometraje
    }

    //CONSTRUCTOR PARA COCHE KM0
    public Vehiculo(String matricula, String marca, String modelo, String color, int kmt) throws VehiculoException {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        //metodo privado para comprobar si el color es válido
        comprobarColor(color);
        //metodo privado para comprobar kilometros
        comprobarKMT(kmt);
        this.nuevo = false; //el coche es km0

    }


    //CONSTRUCTOR MATRICULA Y COLOR RANDOM
    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.nuevo = true;
        this.kilometros = 0;

        //color aleatorio entre los del array
        this.color = colores[(int) (Math.random() * colores.length)];
        //llamo a un metodo privado para generar matricula
        this.matricula = generarMatricula();
    }


    //GETTERS Y SETTERS PARA PROBAR COSAS
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) throws VehiculoException {
        //metodo privado para comprobar si el color es válido
        comprobarColor(color);

    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) throws VehiculoException {
        //metodo privado para comprobar kilometros
        comprobarKMT(kilometros);
    }

    public String[] getColores() {
        return colores;
    }

    private String generarMatricula() {
        //formato 0000AAA
        String matricula = "";

        //con este bucle genero 4 numeros aleatorios y los concateno a la matricula
        for (int i = 0; i < 4; i++) {
            int num = (int) (Math.random() * 10);
            matricula += num;
        }
        //array letras menos Q, Ñ y vocales (letras prohibidas) https://www.elmundo.es/como/2023/05/29/6474831fe4d4d8e75b8b456f.html
        char[] letras = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'};
        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * letras.length);
            char letra = letras[random];
            matricula += letra;
        }
        return matricula;
    }


    public void pasarKM0(int kmt) throws VehiculoException {
        this.nuevo = false; //el coche es KM0
        //metodo privado para comprobar kilometros
        comprobarKMT(kmt);
    }

    public void cambiarColor(String color) throws VehiculoException {
        //metodo privado para comprobar si el color es válido
        comprobarColor(color);

    }

    public boolean actualizarKilometros(int kmt) throws VehiculoException {
        boolean actualizado = false;
        if (nuevo) {
            this.kilometros = 0;
        } else {
            //metodo privado para comprobar kilometros
            comprobarKMT(kmt);
            actualizado = true;
        }
        return actualizado;
    }


    public String formatovehiculo() {
        String cadena = "";
        cadena += this.marca + "; " + this.modelo + "; " + this.color + "; " + this.matricula + "; ";
        if (this.nuevo) {
            cadena += "N; " + this.kilometros;
        } else {
            cadena += "S; " + this.kilometros;
        }
        return cadena;
    }


    public String toString() {
        String cadena = "Vehiculo: " + this.marca + " " + this.modelo + " color " + this.color +
                "\nMatricula: " + this.matricula + "\n";
        if (this.nuevo) {
            cadena += "Es nuevo";
        } else {
            cadena += "Es kilómetro 0";
        }
        return cadena;
    }


    private void comprobarColor(String color) throws VehiculoException {
        boolean colorValido = false;
        int i = 0;
        while (!colorValido && i < colores.length) {
            if (colores[i].equals(color)) {
                colorValido = true;
            }
            i++;
        }
        if (!colorValido) {
            throw new VehiculoException("El color no está en la lista");
        }
            this.color = color;

    }

    private void comprobarKMT(int kmt) throws VehiculoException {
        if (kmt < 0) {
            throw new VehiculoException("Los kilómetros deben ser positivos");
        }
            this.kilometros = kmt;

    }

    private void comprobarMatricula(String matricula) throws VehiculoException {


        //la matricula tiene que tener 7 caracteres
        if (matricula.length() != 7) {
            throw new VehiculoException("Matricula no válida");

        }
        for (int i = 0; i < 4; i++) {
            //los 4 primeros caracteres tienen que ser digitos
            if (!Character.isDigit(matricula.charAt(i))) {
                throw new VehiculoException("Matricula no válida");
            }
        }
        for (int i = 4; i < 7; i++) {
            //los 3 ultimos caracteres tienen que estar en mayusculas
            if (!Character.isUpperCase(matricula.charAt(i))) {
                throw new VehiculoException("Matricula no válida");
            }
        }

        this.matricula=matricula;
    }
}















