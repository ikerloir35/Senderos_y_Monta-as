//Clase que define las excursiones. Dispone de un HashSet para evitar la duplicidad de sus inscripciones.
package pdinfp_modelo;



import java.time.LocalDate;

public class Excursion {

    //Atributos
    //private static AtomicInteger contadorExcursiones = new AtomicInteger(0);
    private String nomExcursion;
    private String descripcion;
    private LocalDate fechaInicio;
    private int numeroDias;
    private Double precioInscripcion;
    //private HashSet<Inscripcion> inscripciones;
    private String codigoExcursion;

    //Constructor
    public Excursion(String codigoExcursion, String nomExcursion, String descripcion, LocalDate fechaInicio, int numeroDias, Double precioInscripcion) {

        this.codigoExcursion = codigoExcursion;
        this.nomExcursion = nomExcursion;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.numeroDias = numeroDias;
        this.precioInscripcion = precioInscripcion;
    }

    //Override del constructor sin asignar numero de excursion, para que sea asignado en la db
    public Excursion(String nomExcursion, String descripcion, LocalDate fechaInicio, int numeroDias, Double precioInscripcion) {

        this.nomExcursion = nomExcursion;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.numeroDias = numeroDias;
        this.precioInscripcion = precioInscripcion;
    }

    //Getters y setters.
    public String getCodigoExcursion() {
        return codigoExcursion;
    }
    public String setCodigoExcursion(Long id){
        codigoExcursion=String.format("EX%04d",id);
        return  codigoExcursion;
    }

    public String getNomExcursion() {
        return nomExcursion;
    }

    public void setNomExcursion(String nomExcursion) {
        this.nomExcursion = nomExcursion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public Double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public void setPrecioInscripcion(Double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }


    //Método específico para eliminar una inscripción almacenada, siempre que sea posterior a la fecha de hoy.
    /* public void eliminarInscripcion(Inscripcion inscripcion){
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaExcursion = inscripcion.getExcursion().getFechaInicio();

        if(fechaExcursion.isAfter(fechaHoy)) {
            inscripciones.remove(inscripcion);
        }
    }*/

    //ToString
    @Override
    public String toString() {
        return "Excursion{" +
                "nomExcursion='" + nomExcursion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", numeroDias=" + numeroDias +
                ", precioInscripcion=" + precioInscripcion +
                ", codigoExcursion='" + codigoExcursion + '\'' +
                '}';
    }
}
