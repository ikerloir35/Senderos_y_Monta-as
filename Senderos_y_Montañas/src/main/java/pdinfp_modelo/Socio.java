//Superclase socio, tiene como subclases Estandar, Federado y Infantil. Añade a cada socio un número de socio,
//automatico y un booleano que marca si el socio está activo o no.
package pdinfp_modelo;



public abstract class Socio {

    //Atributos

    String nombreSocio;
    String nif;
    boolean activo;
    String numeroSocio;


    //Constructor
    public Socio (String nombreSocio, String nif){
        this.nombreSocio = nombreSocio;
        this.nif = nif;
        this.activo = true;
    }


    //Getters y Setters
    public String getNumeroSocio(){ return numeroSocio; }

    public void setNumeroSocio(Long id){
        numeroSocio = String.format("SOC%04d",id);
    }

    public String getNombreSocio() { return nombreSocio; }

    public void setNombreSocio(String nombreSocio) { this.nombreSocio = nombreSocio; }

    public String getNif() { return nif; }

    public void setNif(String nif) { this.nif = nif; }

    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    /*public String getTipoSocio() {
        return tipoSocio;
    }

    public void setTipoSocio(String tipoSocio) {
        this.tipoSocio = tipoSocio;
    }*/

//public AtomicInteger getContadorSocios() { return contadorSocios; }

    //public void setContadorSocios(AtomicInteger contadorSocios) { this.contadorSocios = contadorSocios; }

    //ToString
    @Override
    public String toString() {
        return "Socio{" +
                "nombreSocio='" + nombreSocio + '\'' +
                ", nif='" + nif + '\'' +
                ", numeroSocio='" + numeroSocio + '\'' +
                '}';
    }
}
