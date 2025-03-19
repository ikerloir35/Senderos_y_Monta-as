//Clase que define los objetos tipo socio federado, es subclase de socio. Tiene relación con federaciones.
package pdinfp_modelo;



public class Federado extends Socio {

    //Atributos especificos
    private Federacion federacion;
    private String tipoSocio = "Federado";


    //Constructor
    public Federado(String nombreSocio, String nif, Federacion federacion){
        super(nombreSocio, nif );
        this.federacion = federacion;
    }

    //Getters y Setters
    public Federacion getFederacion() { return federacion; }

    public void setFederacion(Federacion federacion) { this.federacion = federacion; }

    public String getTipoSocio() { return tipoSocio; }

    public void setTipoSocio(String tipoSocio) { this.tipoSocio = tipoSocio; }


    //ToString
    @Override
    public String toString() {
        return "Socio Federado{" +
                "NumeroSocio='" + numeroSocio + '\'' +
                ", nombreSocio='" + nombreSocio + '\'' +
                ", nif='" + nif + '\'' +
                ", tipoSocio='" + tipoSocio + '\'' +
                ", federacion=" + federacion +
                '}';
    }

}
