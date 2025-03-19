package ModeloDTO;
/*
import pdinfp_DAO.MySQLDAO.MySQLDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_modelo.Federacion;
import pdinfp_modelo.Federado;

import java.sql.Connection;
import java.sql.SQLException;

public class FederadoDTO extends SocioDTO{

    private Long idFederado = null; //Primary key y Foreign Key de tabla socios
    private Long federacion_id; //Foreign Key

    public FederadoDTO(String nombreSocio, String nif, boolean activo, Long federacion_id) {
        super(nombreSocio, nif, activo);
        this.federacion_id = federacion_id;
    }

    public Long getIdFederado() {
        return idFederado;
    }

    public void setIdFederado(Long idFederado) {
        this.idFederado = idFederado;
    }

    public Long getFederacion_id() {
        return federacion_id;
    }

    public void setFederacion_id(Long federacion_id) {
        this.federacion_id = federacion_id;
    }

    @Override
    public String toString() {
        return "FederadoDTO{" +
                "idFederados=" + idFederado +
                ", federaciones_id=" + federacion_id +
                '}';
    }
    public Federado toFederado (Connection conexion) throws SQLException, DAOException {
        String nombre = getNombreSocio();
        String nif = getNif();
        MySQLDAOManager mySQLDAOManager = new MySQLDAOManager(conexion);
        FederacionDTO federacionDTO = mySQLDAOManager.getFederacionDAO().obtener(getFederacion_id());
        Federacion federacion = federacionDTO.toFederacion();
        Federado federado = new Federado(nombre,nif,federacion);
        federado.setNumeroSocio(getIdFederado());
        return federado;
    }
    public String setNumeroSocio(Long idFederado){
        String numeroSocio = String.format("SOC%04d",idFederado);
        return numeroSocio;
    }

}*/
