package pdinfp_Entitys;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "excursiones")
public class ExcursionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExcursion", nullable = false)
    private Integer id;

    @Column(name = "nomExcursion", nullable = false, length = 45)
    private String nomExcursion;

    @Column(name = "descripcion", length = 150)
    private String descripcion;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "numeroDias", nullable = false)
    private Integer numeroDias;

    @Column(name = "precioInscripcion", nullable = false)
    private Double precioInscripcion;
    /*
    @OneToMany(mappedBy = "excursionesIdexcursion")
    private Set<InscripcionEntity> inscripciones = new LinkedHashSet<>();
    */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }

    public Double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public void setPrecioInscripcion(Double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }
    /*
    public Set<InscripcionEntity> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Set<InscripcionEntity> inscripciones) {
        this.inscripciones = inscripciones;
    }*/

    public String codigoExcursion (){
        String codigo = String.format("EX%04d",getId());
        return codigo;
    }
    @Override
    public String toString() {
        return "ExcursionEntity{" +
                "id=" + codigoExcursion() +
                ", nomExcursion='" + nomExcursion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", numeroDias=" + numeroDias +
                ", precioInscripcion=" + precioInscripcion +
                '}';
    }
}