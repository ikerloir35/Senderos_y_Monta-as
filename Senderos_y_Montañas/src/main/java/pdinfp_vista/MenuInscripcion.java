//Vista del menu de inscripciones. Es llamado por el controlador de inscripciones.
package pdinfp_vista;


import pdinfp_Entitys.InscripcionEntity;

import java.util.Scanner;

public class MenuInscripcion {


    public static int gestionInscripcion() {
        System.out.println("\nBienvenido al Menu de Gestión de Inscripciones");
        System.out.println("Selecciona una opción:");
        System.out.println("\n\t1. Añadir Inscripcion");
        System.out.println("\t2. Mostrar Inscripcion");
        System.out.println("\t3. Eliminar Inscripcion");
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
    public static String  agregarInscripcion(){
        System.out.println("Introduzca el codigo de excursion para añadir una inscripcion: ");
        String codigo;
        Scanner teclado = new Scanner(System.in);
        codigo = teclado.nextLine();
        return (codigo);
    }

    public static int  mostrarInscripcionesXSocio() {
        System.out.println("Desea ver las inscripciones por socio?");
        System.out.println("1.- Si");
        System.out.println("2.- No");
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
    public static int  mostrarInscripcionesXFecha(){
        System.out.println("Desea ver las inscripciones por rango de fechas?");
        System.out.println("1.- Si");
        System.out.println("2.- No");
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
    public static void mensajeInscripcionSocio(String codigoSocio){
        System.out.println(" El socio "+ codigoSocio +" ya esta inscrito.");
    }

    public static void mensajeFiltro(){
        System.out.println("No ha seleccionado ningún filtro, se mostraran todas las inscripciones");
    }
    public static String borrarInscripcionExcursion(){
        System.out.println("Introduzca el codigo de excursion del que quiera borrar una inscripcion.");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static String borrarInscripcion(){
        System.out.println("Introduzca el codigo de inscripcion que quiera borrar.");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static  void codExcursionNotFound (){
        System.out.println("El codigo de excursión introducido no existe.\n");
    }

    public static void codInscripcionNotFound (){
        System.out.println("El codigo de inscripción introducido no existe.\n");
    }
    public static void printInscripcion(InscripcionEntity inscripcion, int contador){
        System.out.println(contador + "- " + inscripcion);
    }


}
