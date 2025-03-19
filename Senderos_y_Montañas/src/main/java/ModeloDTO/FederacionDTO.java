package ModeloDTO;
/*
import pdinfp_modelo.Federacion;

public class FederacionDTO {

    private Long idFederacion = null; //Primary key
    private String nombreFederacion;

    public FederacionDTO(String nombreFederacion) {

        this.nombreFederacion = nombreFederacion;
    }

    public Long getIdFederacion() {
        return idFederacion;
    }

    public void setIdFederacion(Long idFederacion) {
        this.idFederacion = idFederacion;
    }

    public String getNombreFederacion() {
        return nombreFederacion;
    }

    public void setNombreFederacion(String nombreFederacion) {
        this.nombreFederacion = nombreFederacion;
    }

    @Override
    public String toString() {
        return "FederacionDTO{" +
                "idFederacion=" + idFederacion +
                ", nombreFederacion='" + nombreFederacion + '\'' +
                '}';
    }
    public Federacion toFederacion(){
        String codigoFederacion = String.format("FED%04d",getIdFederacion());
        return new Federacion(getNombreFederacion(),codigoFederacion);
    }
    /*public String getNumeroFederacion(){
        String numeroInscripcion = String.format("FED%04d",getIdFederacion());
        return numeroInscripcion;
    }
}*/
