//Clase que define las inscripciones a las excursiones. Tiene una dependencia con Excursión.

package pdinfp_modelo;



public class Inscripcion {

    //Atributos
    //private static AtomicInteger contadorExcursiones = new AtomicInteger(0);
    private Socio socio;
    private Excursion excursion;
    private String numInscripcion;

    //Constructor
    public Inscripcion(Socio socio, Excursion Excursion) {

        this.socio = socio;
        this.excursion = Excursion;
        //this.numInscripcion = String.format("INS%04d", contadorExcursiones.incrementAndGet());
    }

    //getters y setters.
    public String getNumInscripcion() { return numInscripcion; }

    public String setNumInscripcion(Long id){
        numInscripcion = String.format("INS%04d",id);
        return numInscripcion;
    }

    public Socio getSocio() { return socio; }

    public void setSocio(Socio socio) {
            this.socio = socio;
    }

    public Excursion getExcursion() { return excursion; }

    public void setExcursion(Excursion excursion) { this.excursion = excursion; }

    //ToString
    @Override
    public String toString() {
        return "Inscripcion{" +
                "socio=" + socio +
                ", excursion=" + excursion +
                ", numInscripcion='" + numInscripcion + '\'' +
                '}';
    }

}
