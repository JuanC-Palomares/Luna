package alumnos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    static Scanner teclado = new Scanner(System.in);

    public static void saveStudent() throws IOException {
        System.out.println("------- Registrar Alumno --------");
        System.out.println("Ingrese los datos completos");

        Alumno alumno = new Alumno();

        System.out.println("Numero de control: ");
        alumno.numeroControl = teclado.nextLine().trim().toUpperCase();

        System.out.println("Nombre: ");
        alumno.nombre = teclado.nextLine().trim().toUpperCase();

        System.out.println("Apellido: ");
        alumno.apellidos = teclado.nextLine().trim().toUpperCase();

        System.out.println("Carrera: ");
        alumno.carrera = teclado.nextLine().trim().toUpperCase();

        System.out.println("email: ");
        alumno.email = teclado.nextLine().trim().toUpperCase();

        // Se valida que los datos esten completos
        if (!alumno.numeroControl.isEmpty() || !alumno.numeroControl.isEmpty()
                || !alumno.numeroControl.isEmpty() || !alumno.numeroControl.isEmpty()
                || !alumno.numeroControl.isEmpty()) {
            
            String[] searchAlumno = getStudent(alumno.numeroControl);
            
            if (searchAlumno == null) {
                            if (saveAlumnoDocument(alumno)) {
                System.out.println("Alumno guardado correctamente");
            } else {
                System.err.println("Error al guardar el alumno");
            }
            } else {
                System.err.println("El alumno ya se encuentra registrado");
            }
             


        } else {
            System.err.println("Los datos deben estar completos");
        }
    }

    public static String[] getStudent(String numeroControl) throws IOException {
        String alumno;
        String[] alumnoBuscado = null;
        File file = new File("src/resources/alumnos.txt");

        if (file.exists()) {

            try {
                FileReader fileReader = new FileReader("src/resources/alumnos.txt");
                BufferedReader b = new BufferedReader(fileReader);
                while ((alumno = b.readLine()) != null) {
                    String[] matricula = alumno.split(";");
                    if (matricula[0].equals(numeroControl)) {
                        alumnoBuscado = matricula;
                        break;
                    }
                }
                b.close();
            } catch (FileNotFoundException ex) {
                System.err.println("Error al leer el archivo");
            }

        }

        return alumnoBuscado;
    }

    public static boolean isExit() {
        System.out.println("¿Desea salir del programa? (si/no)");

        boolean isExit = true;

        String confirmation = teclado.nextLine().toUpperCase().trim();

        if (confirmation != null && !confirmation.isEmpty() && confirmation.equals("SI")) {
            isExit = false;
        }

        return isExit;
    }

    private static boolean saveAlumnoDocument(Alumno alumno) {

        boolean isSuccess = true;

        try {

            File file = new File("src/resources/alumnos.txt");

            // Si el archivo no existe es creado
            if (file.exists()) {
                FileWriter flwriter = new FileWriter("src/resources/alumnos.txt", true);
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.newLine();
                bfwriter.write(alumno.numeroControl + ";" + alumno.nombre + ";"
                        + alumno.apellidos + ";" + alumno.carrera + ";" + alumno.email);
                bfwriter.close();
            } else {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(alumno.numeroControl + ";" + alumno.nombre + ";"
                        + alumno.apellidos + ";" + alumno.carrera + ";" + alumno.email);
                bw.close();
            }

        } catch (Exception e) {
            isSuccess = false;
        }

        return isSuccess;
    }

}
