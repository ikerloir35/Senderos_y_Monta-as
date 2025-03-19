//Subclase de Socio, que registra los socios infatiles. Requiere de un socio ya inscrito que haga de tutor.
package pdinfp_modelo;

public class Infantil extends Socio {

    //Atributos
    private Socio socioTutor;
    private String tipoSocio = "Infantil";

    //Constructor
    public Infantil(String nombreSocio, Socio socioTutor) {
        super(nombreSocio, "n/d");
        this.socioTutor = socioTutor;
    }

    //Getters y Setters



    public Socio getSocioTutor() { return socioTutor; }

    public void setSocioTutor(Socio socioTutor) { this.socioTutor = socioTutor; }

    public String getTipoSocio() { return tipoSocio; }

    public void setTipoSocio(String tipoSocio) { this.tipoSocio = tipoSocio; }

    //ToString
    @Override
    public String toString() {
        return "Socio Infantil{" +
                "NumeroSocio='" + numeroSocio + '\'' +
                ", nombreSocio='" + nombreSocio + '\'' +
                ", nif='" + nif + '\'' +
                ", tipoSocio='" + tipoSocio + '\'' +
                ", socioTutor=" + socioTutor.getNombreSocio() + ", socioTutorNIF=" + socioTutor.getNif() +
                '}';
    }
}
