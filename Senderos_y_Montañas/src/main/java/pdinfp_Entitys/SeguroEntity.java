package pdinfp_Entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "seguros")
public class SeguroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSeguro", nullable = false)
    private Integer id;

    @Column(name = "nombreSeguro", length = 45)
    private String nombreSeguro;

    @Column(name = "precio", nullable = false)
    private Double precio;
    /*
    @OneToMany(mappedBy = "segurosIdseguro")
    private Set<EstandarEntity> estandars = new LinkedHashSet<>();
    */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSeguro() {
        return nombreSeguro;
    }

    public void setNombreSeguro(String nombreSeguro) {
        this.nombreSeguro = nombreSeguro;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    /*
    public Set<EstandarEntity> getEstandars() {
        return estandars;
    }

    public void setEstandars(Set<EstandarEntity> estandars) {
        this.estandars = estandars;
    }*/
}