package ModeloDTO;
/*
import pdinfp_DAO.MySQLDAO.MySQLDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_modelo.Estandar;
import pdinfp_modelo.Seguro;

import java.sql.Connection;
import java.sql.SQLException;

@Deprecated
public class EstandarDTO extends SocioDTO {

    private Long idEstandar = null;
    private Long idSeguro;

    public EstandarDTO(String nombreSocio, String nif, boolean activo, Long idSeguro) {
        super(nombreSocio, nif, activo);
        this.idSeguro = idSeguro;
    }


    public Long getIdEstandar() {
        return idEstandar;
    }


    public void setIdEstandar(Long idSocio) {
        this.idEstandar = idSocio;
    }

    public Long getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(Long idSeguro) {
        this.idSeguro = idSeguro;
    }

    @Override
    public String toString() {
        return "EstandarDTO{" +
                "idEstandar=" + idEstandar +
                ", idSeguro=" + idSeguro +
                '}';
    }
    public Estandar toEstandar(Connection connection) throws SQLException, DAOException {
        String nombre = super.getNombreSocio();
        String nif = super.getNif();
        MySQLDAOManager mySQLDAOManager = new MySQLDAOManager(connection);
        SeguroDTO seguroDTO = mySQLDAOManager.getSeguroDAO().obtener(getIdSeguro());
        Seguro seguro = seguroDTO.toSeguro();
        Estandar estandar = new Estandar(nombre,nif,seguro);
        estandar.setNumeroSocio(getIdEstandar());
        return estandar;
    }
    public String setNumeroSocio(Long idEstandar){
        String numeroSocio = String.format("SOC%04d",idEstandar);
        return numeroSocio;
    }
}*/
