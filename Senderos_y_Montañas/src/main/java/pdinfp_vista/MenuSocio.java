//Vista del menu socio, es llamado por el ControladorSocio

package pdinfp_vista;


import java.util.Scanner;

public class MenuSocio {

    public static int menuGestionSocio() {

        System.out.println("\nBienvenido al Menu de Gestión de Socios");
        System.out.println("Selecciona una opción:");
        System.out.println("\n\t1. Añadir Socio");
        System.out.println("\t2. Mostrar Socio");
        System.out.println("\t3. Modificar Socio"); //incluye modificar seguro
        System.out.println("\t4. Eliminar Socio");
        System.out.println("\t5. Mostrar Factura Mensual de un Socio");
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

    public static int  anadirSocio(){
        System.out.println("Introduzca el tipo de socio a crear: ");
        System.out.println("\n\t1. Estandar");
        System.out.println("\t2. Federado");
        System.out.println("\t3. Infantil");
        System.out.println("\t0. Salir\n");
        int opcion = 10;
        try {
            Scanner scanner = new Scanner(System.in);
            opcion = Integer.parseInt(scanner.nextLine());

        }
        catch (NumberFormatException nfe)
        {
            UtilidadesVista.errorDatos();

        }return (opcion);

    }

    public static int mostrarSocio(){

        System.out.println("Que tipo de socio quiere mostrar? ");
        System.out.println("\n\t1. Todos los Estandar");
        System.out.println("\t2. Todos los Federados");
        System.out.println("\t3. Todos los Infantiles");
        System.out.println("\t4. Todos los Socios");
        System.out.println("\t5. Un Socio");
        System.out.println("\t0. Salir");

        int opcion = 10;
        try {
            Scanner scanner = new Scanner(System.in);
            opcion = Integer.parseInt(scanner.nextLine());

        }
        catch (NumberFormatException nfe)
        {
            UtilidadesVista.errorDatos();

        }return (opcion);
    }

    public static int cambiarSocio(){

        System.out.println("Que quieres cambiar de un socio? ");
        System.out.println("\n\t1. El seguro de un socio Estandar");
        System.out.println("\t2. La federación de un socio Federado");
        System.out.println("\t3. El tutor de un socio Infantil");
        System.out.println("\t0. Salir");

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

    public static String preguntaSeguro() {
        System.out.println("Introduce el que tipo de seguro desea adquirir: Completo o Parcial");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static void errorSeguro(){
        System.out.println("Ha introducido mal el nombre del seguro."); }
    public static String preguntaNombreFederacion() {
        System.out.println("Indica el nombre de la nueva federación.");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static String preguntaCodigoFederacion() {
        System.out.println("Indica el código de la federación a la que pertenece el socio.");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static String preguntaNifTutor() {
        System.out.println("Indica el NIF del socio Tutor");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static void errorFederacion(){
        System.out.println("Ha introducido mal el codigo de la federacion.");}
    public static String eliminarSocio(){
        System.out.println("Introduzca el codigo del socio a eliminar");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }

    public static void desactivarSocio(){
        System.out.println("El socio esta inscrito en alguna excursión, no se puede desactivar."); }
    public static void infantilRepetido(){
        System.out.println("El socio tutor ya tiene un socio infantil vinculado con ese nombre."); }
    public static void nifNoCorrespondeAClase(){
        System.out.println("El nif facilitado no corresponde a la clase a la que deseas realizar el cambio.");
    }

}
