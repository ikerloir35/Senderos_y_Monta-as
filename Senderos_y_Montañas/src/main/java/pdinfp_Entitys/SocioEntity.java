package pdinfp_Entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "socios")
public class SocioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSocio", nullable = false)
    private Integer id;

    @Column(name = "nombreSocio", nullable = false, length = 50)
    private String nombreSocio;

    @Column(name = "nif", nullable = false, length = 12)
    private String nif;

    @Column(name = "activo", nullable = false)
    private Byte activo;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    /*
    @ManyToMany(mappedBy = "socios")
    private Set<SeguroEntity> seguros = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "socios")
    private Set<FederacionEntity> federaciones = new LinkedHashSet<>();

    @OneToOne(mappedBy = "socios")
    private InfantilEntity infantile;

    @OneToMany(mappedBy = "sociosIdsocio")
    private Set<InscripcionEntity> inscripciones = new LinkedHashSet<>();
    */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /*
    public Set<SeguroEntity> getSeguros() {
        return seguros;
    }

    public void setSeguros(Set<SeguroEntity> seguros) {
        this.seguros = seguros;
    }

    public Set<FederacionEntity> getFederaciones() {
        return federaciones;
    }

    public void setFederaciones(Set<FederacionEntity> federaciones) {
        this.federaciones = federaciones;
    }


    public Set<InscripcionEntity> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Set<InscripcionEntity> inscripciones) {
        this.inscripciones = inscripciones;
    }*/

    public String codigoSocio (){
        String codigo = String.format("SOC%04d",getId());
        return codigo;
    }

    public String toString() {
        return "SocioEntity{"+ " id= "+ codigoSocio() + '\'' +
                "nombreSocio='" + nombreSocio + '\'' +
                ", nif='" + nif + '\'' +
                ", tipo='" + tipo +
                '}';
    }
}