//Vista del menu principal del programa. Es llamado por el controladorDAO Principal
package pdinfp_vista;

import java.util.Scanner;

public class MenuPrincipal {

    public static int menuPrincipal(){

        System.out.println("************************************");
        System.out.println("  Bienvenido a Senderos y Montañas");
        System.out.println("************************************");
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("\nSelecciona una opción:");
        System.out.println("\n\t1. Gestión de Excursiones");
        System.out.println("\t2. Gestión de Socios");
        System.out.println("\t3. Gestión de Inscripciones");
        System.out.println("\t0. Salir\n");
       int opcion = 10;
        try {
            Scanner scanner = new Scanner(System.in);
            opcion = Integer.parseInt(scanner.nextLine());

        }
        catch (NumberFormatException nfe)
        {
            UtilidadesVista.errorDatos();

            //erase the invalid input and avoid infite loop
        }return (opcion);


    }


}
