package pdinfp_Entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "inscripciones")
public class InscripcionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInscripcion", nullable = false)
    private Integer id;

    @Column(name="socios_idSocio", nullable = false)
    private Integer socios_Idsocio;

    @Column(name="excursiones_idExcursion",nullable = false)
    private Integer excursiones_idExcursion;
    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socios_idSocio", nullable = false)
    private SocioEntity sociosIdsocio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "excursiones_idExcursion", nullable = false)
    private ExcursionEntity excursionesIdexcursion;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    /*
    public SocioEntity getSociosIdsocio() {
        return sociosIdsocio;
    }

    public void setSociosIdsocio(SocioEntity sociosIdsocio) {
        this.sociosIdsocio = sociosIdsocio;
    }

    public ExcursionEntity getExcursionesIdexcursion() {
        return excursionesIdexcursion;
    }

    public void setExcursionesIdexcursion(ExcursionEntity excursionesIdexcursion) {
        this.excursionesIdexcursion = excursionesIdexcursion;
    }*/

    public Integer getSocios_Idsocio() {
        return socios_Idsocio;
    }
    public String getCodigo_idSocio(){
        String codigo = String.format("SOC%04d",getSocios_Idsocio());
        return codigo;
    }

    public void setSocios_Idsocio(Integer socios_Idsocio) {
        this.socios_Idsocio = socios_Idsocio;
    }

    public String getCodigo_idExcursion(){
        String codigo = String.format("EX%04d",getExcursiones_idExcursion());
        return codigo;
    }

    public Integer getExcursiones_idExcursion() {
        return excursiones_idExcursion;
    }

    public void setExcursiones_idExcursion(Integer excursiones_idExcursion) {
        this.excursiones_idExcursion = excursiones_idExcursion;
    }

    public String codigoInscripcion() {
        String codigo =  String.format("INS%04d",getId());
        return codigo;
    }
    @Override
    public String toString() {
        return "InscripcionEntity{" +
                "id=" + codigoInscripcion()+ " " + "ID socio= " + getCodigo_idSocio() +
                ", Id excursion=" + getCodigo_idExcursion() +
                '}';
    }
}