package pdinfp_Entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "infantiles")
public class InfantilEntity {
    @Id
    @Column(name = "socios_idSocio", nullable = false)
    private Integer id;
    /*
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "socios_idSocio", nullable = false)
    private SocioEntity socios;*/

    @Column(name = "nifTutor", nullable = false, length = 12)
    private String nifTutor;

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
    }*/

    public String getNifTutor() {
        return nifTutor;
    }

    public void setNifTutor(String nifTutor) {
        this.nifTutor = nifTutor;
    }

}