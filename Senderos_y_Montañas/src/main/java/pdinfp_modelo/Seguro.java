//Clase que define los diferentes seguros solicitados. Tiene relación solo con los socios Estandar.
package pdinfp_modelo;

public class Seguro {
    //Atributos
    public String nombreSeguro;
    public Double precio;

    //Constructor
    public Seguro(String nombreSeguro, Double precio){
        this.nombreSeguro = nombreSeguro;
        this.precio = precio;
    }
    //Getters y Setters.
    public String getNombreSeguro() {
        return nombreSeguro;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setNombreSeguro(String nombreSeguro) {
        this.nombreSeguro = nombreSeguro;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    //ToString
    @Override
    public String toString() {
        return "Seguro{" +
                "Nombre del pdinfp_modelo.Seguro = '" + nombreSeguro + '\'' +
                ", precio = " + precio +
                '}';
    }
}
