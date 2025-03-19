package pdinfp_Entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "federados")
public class FederadoEntity {
    @Id
    @Column(name = "socios_idSocio", nullable = false)
    private Integer id;

    @Column(name= "federaciones_idFederacion", nullable = false)
    private Integer idFederacion;

    /*
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socios_idSocio", nullable = false)
    private SocioEntity socios;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "federaciones_idFederacion", nullable = false)
    private FederacionEntity federacionesIdfederacion;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    /*
    public SocioEntity getSocios() {
        return socios;
    }

    public void setSocios(SocioEntity socios) {
        this.socios = socios;
    }

    public FederacionEntity getFederacionesIdfederacion() {
        return federacionesIdfederacion;
    }

    public void setFederacionesIdfederacion(FederacionEntity federacionesIdfederacion) {
        this.federacionesIdfederacion = federacionesIdfederacion;
    }*/

    public Integer getIdFederacion() {
        return idFederacion;
    }

    public void setIdFederacion(Integer idFederacion) {
        this.idFederacion = idFederacion;
    }
}