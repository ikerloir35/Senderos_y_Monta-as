package pdinfp_Entitys;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "federaciones")
public class FederacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFederacion", nullable = false)
    private Integer id;

    @Column(name = "nombreFederacion", nullable = false, length = 45)
    private String nombreFederacion;

    @ManyToMany
    @JoinTable(name = "federados",
            joinColumns = @JoinColumn(name = "federaciones_idFederacion"),
            inverseJoinColumns = @JoinColumn(name = "socios_idSocio"))
    private Set<SocioEntity> socios = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreFederacion() {
        return nombreFederacion;
    }

    public void setNombreFederacion(String nombreFederacion) {
        this.nombreFederacion = nombreFederacion;
    }

    public Set<SocioEntity> getSocios() {
        return socios;
    }

    public void setSocios(Set<SocioEntity> socios) {
        this.socios = socios;
    }

}