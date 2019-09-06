/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumnos;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        menu();
        
    }
    
    private static void menu () throws IOException {
        
        boolean isExit = true;
        int option = 0;
        
        do{
            // Se muestran la opciones que tendra el usuario
            System.out.println("Seleccion '1': Alta de usuario");
            System.out.println("Seleccion '2': Consultar usuario");
            System.out.println("Seleccion '3': Salir del programa");
            
            try {
                //Se lee la opcion elegida por el usuario
                option = Integer.valueOf(teclado.nextLine());
                
                switch (option) {
                    case 1:
                            Util.saveStudent();
                        break;
                    case 2:
                            System.out.println("Ingrese el numero de control del alumno: ");
                            String numeroControl = teclado.nextLine().toUpperCase().trim();
                            String [] alumno = Util.getStudent(numeroControl);
                            
                            if (alumno != null) {
                                                            System.out.println("NumeroControl: " + alumno[0]);
                            System.out.println("Nombre: " + alumno[1]);
                            System.out.println("Apellidos: " + alumno[2]);
                            System.out.println("Carrera: " + alumno[3]);
                            System.out.println("Email: " + alumno[4]);
                            } else {
                                System.err.println("El alumno no se encuentra registrado");
                            }
                            

                        break;
                    case 3:
                        isExit = Util.isExit();
                        break;
                    default:
                        System.err.println("Seleccione una opcion valida");
                        break;
                }
                
            } catch (NumberFormatException e) {
                System.err.println("Seleccione una opcion valida");
            }
            
        } while (isExit);
    }
}
