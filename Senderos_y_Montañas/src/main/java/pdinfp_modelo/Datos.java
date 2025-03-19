//Clase que almacena los datos entrados.
package pdinfp_modelo;

import java.util.HashSet;

public class Datos {

    //Atributos.
    public HashSet<Socio> listaSocios;
    public HashSet<Federacion> listaFederaciones;
    public HashSet<Seguro> listaSeguros;
    public HashSet<Excursion> listaExcursiones;

    //Constructor
    public Datos() {
        this.listaSocios = new HashSet<>();
        this.listaFederaciones = new HashSet<>();
        this.listaSeguros = new HashSet<>();
        this.listaExcursiones = new HashSet<>();
    }

    //Getters y setters.
    public HashSet<Socio> getListaSocios() {
        return listaSocios;
    }

    public void setListaSocios(HashSet<Socio> listaSocios) {
        this.listaSocios = listaSocios;
    }

    public HashSet<Federacion> getListaFederaciones() {
        return listaFederaciones;
    }

    public void setListaFederaciones(HashSet<Federacion> listaFederaciones) {
        this.listaFederaciones = listaFederaciones;
    }

    public HashSet<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(HashSet<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    public HashSet<Excursion> getListaExcursiones() {
        return listaExcursiones;
    }

    public void setListaExcursiones(HashSet<Excursion> listaExcursiones) {
        this.listaExcursiones = listaExcursiones;
    }

}
