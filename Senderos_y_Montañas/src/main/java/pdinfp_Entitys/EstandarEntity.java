package pdinfp_Entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estandars")
public class EstandarEntity {
    @Id
    @Column(name = "socios_idSocio", nullable = false)
    private Integer id;

    @Column(name = "seguros_idSeguro", nullable = false)
    private Integer idSeguro;

    /*
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socios_idSocio", nullable = false)
    private SocioEntity socios;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seguros_idSeguro", nullable = false)
    private SeguroEntity segurosIdseguro;*/

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

    public SeguroEntity getSegurosIdseguro() {
        return segurosIdseguro;
    }

    public void setSegurosIdseguro(SeguroEntity segurosIdseguro) {
        this.segurosIdseguro = segurosIdseguro;
    }*/

    public Integer getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(Integer idSeguro) {
        this.idSeguro = idSeguro;
    }
}