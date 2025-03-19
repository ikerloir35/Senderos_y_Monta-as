package ModeloDTO;
/*
import pdinfp_modelo.Excursion;

import java.sql.Date;
import java.time.LocalDate;

public class ExcursionDTO {

    private Long idExcursion = null; //Primary key
    private String nomExcursion;
    private String descripcion;
    private Date fechaInicio;
    private int numeroDias;
    private double precioInscripcion;

    public ExcursionDTO(String nomExcursion, String descripcion, Date fechaInicio, int numeroDias, double precioInscripcion) {
        this.nomExcursion = nomExcursion;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.numeroDias = numeroDias;
        this.precioInscripcion = precioInscripcion;
    }

    public Long getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(Long idExcursion) {
        this.idExcursion = idExcursion;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public void setPrecioInscripcion(double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }

    @Override
    public String toString() {
        return "ExcursionDTO{" +
                "idExcursiones=" + idExcursion +
                ", nomExcursion='" + nomExcursion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", numeroDias=" + numeroDias +
                ", precioInscripcion=" + precioInscripcion +
                '}';
    }
    public Excursion toExcursion(){
        String nomExcursion = getNomExcursion();
        String descripcion = getDescripcion();
        LocalDate fechaInicio = getFechaInicio().toLocalDate();

        int numeroDias = getNumeroDias();
        Double precioInscripcion = getPrecioInscripcion();
        String codigoExcursion = String.format("EX%04d",getIdExcursion());

        return new Excursion(codigoExcursion, nomExcursion,descripcion,fechaInicio,numeroDias,precioInscripcion);
    }
}*/
