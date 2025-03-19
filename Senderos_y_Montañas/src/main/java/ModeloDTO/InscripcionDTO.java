package ModeloDTO;
/*
import pdinfp_DAO.MySQLDAO.MySQLDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_modelo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InscripcionDTO {

    private Long idInscripcion = null; //Primary key
    private Long socios_idSocio;
    private Long idExcursion;

    public InscripcionDTO(Long idSocio,Long idExcursion) {
        this.socios_idSocio = idSocio;
        this.idExcursion = idExcursion;
    }

    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Long getIdSocio() {
        return socios_idSocio;
    }

    public void setIdSocio(Long idSocio) {
        this.socios_idSocio = idSocio;
    }

    public Long getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(Long idExcursion) {
        this.idExcursion = idExcursion;
    }

    @Override
    public String toString() {
        return "InscripcionDTO{" +
                "idInscripcion=" + idInscripcion +
                ", idSocio=" + socios_idSocio +
                ", idExcursion=" + idExcursion +
                '}';
    }
    public Inscripcion toInscripcion(Connection conexion) throws DAOException, SQLException {
        MySQLDAOManager mySQLDAOManager = new MySQLDAOManager(conexion);
        List<SocioDTO> listaSocios = mySQLDAOManager.getSocioDAO().obtenerTodos();
        List<ExcursionDTO> listaExcursiones = mySQLDAOManager.getExcursionDAO().obtenerTodos();

        Socio noSocio = null;
        Excursion excursion = null;
        for (ExcursionDTO excursionDTO : listaExcursiones){
            if (excursionDTO.getIdExcursion().equals(getIdExcursion())){
                excursion = excursionDTO.toExcursion();
            }
        }

        for (SocioDTO socioDTO : listaSocios){
            if (socioDTO.getIdSocio().equals(getIdSocio())){
                if (socioDTO.getTipo().contentEquals("Estandar")){
                    EstandarDTO estandarDTO = mySQLDAOManager.getEstandarDAO().obtener(socioDTO.getIdSocio());
                    Estandar estandar = estandarDTO.toEstandar(conexion);
                    estandar.setNumeroSocio(socioDTO.getIdSocio());
                    Inscripcion inscripcion = new Inscripcion(estandar,excursion);
                    inscripcion.setNumInscripcion(getIdInscripcion());
                    return inscripcion;
                }
                if (socioDTO.getTipo().contentEquals("Infantil")){
                    InfantilDTO infantilDTO = mySQLDAOManager.getInfantilDAO().obtener(socioDTO.getIdSocio());
                    Infantil infantil = infantilDTO.toInfantil(conexion);
                    infantil.setNumeroSocio(socioDTO.getIdSocio());
                    Inscripcion inscripcion = new Inscripcion(infantil,excursion);
                    inscripcion.setNumInscripcion(getIdInscripcion());
                    return inscripcion;
                }
                if (socioDTO.getTipo().contentEquals("Federado")){
                    FederadoDTO federadoDTO = mySQLDAOManager.getFederadoDAO().obtener(socioDTO.getIdSocio());
                    Federado federado = federadoDTO.toFederado(conexion);
                    federado.setNumeroSocio(socioDTO.getIdSocio());
                    Inscripcion inscripcion = new Inscripcion(federado,excursion);
                    inscripcion.setNumInscripcion(getIdInscripcion());
                    return inscripcion;

                }
            }
        }
        return new Inscripcion(noSocio,excursion);
    }
    public String setNumeroInscripcion(Long idInscripcion){
        String numeroInscripcion = String.format("INS%04d",idInscripcion);
        return numeroInscripcion;
    }
}*/


