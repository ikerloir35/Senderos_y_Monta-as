//Clase que define los socios Estandar, subclase de socio. Tiene relación con seguros.
package pdinfp_modelo;



public class Estandar extends Socio{

    //Atributos espcificos
    private Seguro seguroContratado;
    private String tipoSocio = "Estandar";

    //Constructor
    public Estandar(String nombre, String nif, Seguro seguroContratado){
        super(nombre, nif);
        this.seguroContratado = seguroContratado;
    }

    //Getters y Setters
    public Seguro getSeguroContratado() { return seguroContratado; }

    public void setSeguroContratado(Seguro seguroContratado) { this.seguroContratado = seguroContratado; }

    public String getTipoSocio() { return tipoSocio; }

    public void setTipoSocio(String tipoSocio) { this.tipoSocio = tipoSocio; }

    //ToString
    @Override
    public String toString() {
        return "Socio Estandar{" +
                "NumeroSocio='" + numeroSocio + '\'' +
                ", nombreSocio='" + nombreSocio + '\'' +
                ", nif='" + nif + '\'' +
                ", tipoSocio='" + tipoSocio + '\'' +
                ", seguroContratado=" + seguroContratado +
                '}';
    }

}
