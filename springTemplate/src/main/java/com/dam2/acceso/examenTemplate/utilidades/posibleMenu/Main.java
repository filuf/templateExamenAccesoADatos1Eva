package com.dam2.acceso.examenTemplate.utilidades.posibleMenu;

import java.util.Scanner;

/**
 * Menú prefabricado por si se pide
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (true) {
            // Mostrar el menú
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Opción 1");
            System.out.println("2. Opción 2");
            System.out.println("3. Opción 3");
            System.out.println("4. Opción 4");
            System.out.println("5. Opción 5");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            // Leer la opción del usuario
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // limpiar entrada incorrecta
                continue;
            }

            // Procesar la opción
            switch (opcion) {
                case 1:
                    System.out.println("Ha seleccionado la Opción 1");
                    break;
                case 2:
                    System.out.println("Ha seleccionado la Opción 2");
                    break;
                case 3:
                    System.out.println("Ha seleccionado la Opción 3");
                    break;
                case 4:
                    System.out.println("Ha seleccionado la Opción 4");
                    break;
                case 5:
                    System.out.println("Ha seleccionado la Opción 5");
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return; // salir del main y terminar el programa
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

}
