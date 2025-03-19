//Vista del menu excursiones. Es llamado por el controlador de Excursiones.

package pdinfp_vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuExcursion {

    public static int menuExcursion(){

        System.out.println("\nBienvenido al Menu de Gestión de Excursiones");
        System.out.println("\nSelecciona una opción:");
        System.out.println("\n\t1. Añadir Excursión");
        System.out.println("\t2. Mostrar Excursión");
        System.out.println("\t3. Modificar Excusión");
        System.out.println("\t4. Eliminar Excursión");
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
    public static String preguntaNombre() {System.out.println("Introduce el nombre de la excursión");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);}
    public static String preguntaDescripcion() {System.out.println("Introduce la descripción");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);}

    public static LocalDate preguntaFechaInicio() {System.out.println("Introduce la fecha de inicio, en formato dd-MM-yyyy");
        String fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaDate = LocalDate.now();
        try {

            Scanner teclado = new Scanner(System.in);
            fecha = teclado.nextLine();
            fechaDate = LocalDate.parse(fecha, formatoFecha);

        } catch (DateTimeParseException dtpe){
            UtilidadesVista.errorDatos();
        }
        return (fechaDate);
    }

    public static int preguntaNumeroDias() {System.out.println("Introduce el número de días de la excursión");
        Scanner teclado = new Scanner(System.in);
        int numeroDias = 0;
        try{
            numeroDias = Integer.parseInt(teclado.nextLine());
            if(numeroDias < 0){
            UtilidadesVista.errorNumeroNegativo();
            }
        } catch (NumberFormatException nfe) {
        UtilidadesVista.errorDatos();}
        return (numeroDias);
    }

    public static Double preguntaPrecioInscripcion() {System.out.println("Introduce el precio de la inscripcion");
        Scanner teclado = new Scanner(System.in);
        Double precioInscripcion = 0.00;
        try {
            precioInscripcion = Double.parseDouble(teclado.nextLine());
            if(precioInscripcion < 0){
                UtilidadesVista.errorNumeroNegativo();
            }
        } catch (NumberFormatException nfe) {
            UtilidadesVista.errorDatos();
        }return(precioInscripcion);
    }

    public static String modificarCodExcurion(){
        System.out.println("Introduzca el codigo de excursion que quiere modificar: ");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static String eliminarExcursion(){
        System.out.println("Introduzca el codigo de excursion que quiere eliminar: ");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static void errorEliminarExcursion(){
        System.out.println("No se puede eliminar una excursion ya realizada");
    }
    public static void errorExcursion(){
        System.out.println("Introduzca los nuevos valores para la excursion o marque 0 para dejarla como esta: ");
    }

    public static void errorModificarFecha() {
        System.out.println("La fecha propuesta es anterior a la fecha actual, no se puede realizar el cambio"); }
    public static void errorExcursionExiste(){
        System.out.println("\nEl codigo de excursion no existe.");
    }
}
