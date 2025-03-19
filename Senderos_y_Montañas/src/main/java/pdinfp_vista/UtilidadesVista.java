//Vista de prints por pantalla genéricos, casi todos los controladores llaman a esta vista.

package pdinfp_vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UtilidadesVista {

    //Metodos genericos Vista

    public static String preguntaNombreSocio() { System.out.println("Introduce el Nombre");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static String preguntaNuevoNifTutor() { System.out.println("Introduce el nif del nuevo Tutor");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);}
    public static String preguntaNif() {System.out.println("Introduce el nif");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);}
    public static String preguntaNumeroSocio() {System.out.println("Introduce el numero de socio");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }
    public static String preguntaNumeroSocioCambio() {System.out.println("Introduce el numero de socio Infantil a cambiar.");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);}
    public static void informarExito() { System.out.println("Operación realizada con exito"); }
    public static void errorCancelacion(){ System.out.println("Operacion cancelada"); }

    public static void seleccionOpciones(){
        System.out.println("Seleccione una de las opciones.");
    }
    public static LocalDate fechaInicialConsulta(){
        System.out.println("Introduzca la fecha inicial de la consulta, utilize el formato dd-MM-yyyy");
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
    public static LocalDate fechaFinalConsulta(){
        System.out.println("Introduzca la fecha final de la consulta, utilize el formato dd-MM-yyyy");
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
    public static void mensajeDespedida(){
        System.out.println("Hasta la proxima");
    }

    //Errores

    public static void errorDatos() { System.out.println("El dato introducido es erroneo, revisa el formato y prueba otra vez."); }

    public static void registroNif() { System.out.println("Este NIF ya esta registrado"); }

    public static void socioNoEncontrado() {System.out.println("Socio no encontrado"); }

    public static void errorTutor() {System.out.println("Tutor no encontrado"); }

    public static void errorModificacion(){
        System.out.println("No se ha realizado ninguna modificacion");
    }

    //Facturas

    public static String codigoFactura(){
        System.out.println("Introduzca el codigo del socio para calcular su factura");
        String entrada;
        Scanner teclado = new Scanner(System.in);
        entrada = teclado.nextLine();
        return (entrada);
    }

    public static void mensajeImporte(Double total){
        System.out.println(String.format("El importe a pagar este mes es de %.2f",total));
    }
    public static void errorNumeroNegativo(){
        System.out.println("Error: el numero no puede ser negativo. Ingresa otro numero.");}
    public static void socioDesactivado(){
        System.out.println("El socio no esta activo.");}


}
