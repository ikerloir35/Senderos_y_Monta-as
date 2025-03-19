package pdinfp_vista;

public class BBDDVista {

    public static void conexionSi(){
        System.out.println("Conexion realizada correctamente");
    }
    public static void conexionNo(){
        System.out.println("Conexion NO realizada");
    }
    public static void conexionTerminada(){
        System.out.println("Conexion cerrada correctamente");
    }

    public static String errorEnSQL(){return( "Error en SQL"); }
    public static void tareaOK() {
        System.out.println("Tarea completada");}
    public static String tareaFail() {return ("Tarea no completada.");}


}
