//Clase que define las federaciones. Tiene relación solo con los socios federados.
package pdinfp_modelo;



public class Federacion {
    //Atributos.
    private static int contadorFederaciones;
    public String nombreFederacion;
    public String codigoFederacion;

    //Constructor
    public Federacion(String nombreFederacion, String codigoFederacion) {
        this.nombreFederacion = nombreFederacion;
        this.codigoFederacion = codigoFederacion;
    }

    //Getters y setters.
    public String getNombreFederacion() { return nombreFederacion; }

    public void setNombreFederacion(String nombreFederacion) { this.nombreFederacion = nombreFederacion; }

    public void setCodigoFederacion(String codigoFederacion) {
        this.codigoFederacion = codigoFederacion;
    }

    public String getCodigoFederacion() { return codigoFederacion; }

    //ToString
    @Override
    public String toString() {
        return "Federacion{" +
                "nombreFederacion='" + nombreFederacion + '\'' +
                ", codigoFederacion='" + codigoFederacion + '\'' +
                '}';
    }
}
