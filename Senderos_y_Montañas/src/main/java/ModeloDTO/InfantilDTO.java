package ModeloDTO;
/*
import pdinfp_DAO.MySQLDAO.MySQLDAOManager;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_modelo.Estandar;
import pdinfp_modelo.Federado;
import pdinfp_modelo.Infantil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class InfantilDTO extends SocioDTO{

    private Long idInfantil = null; //Primary Key
    private String nifSocioTutor;

    public InfantilDTO(String nombreSocio, String nif, boolean activo, String nifSocioTutor) {
        super(nombreSocio, nif, activo);
        this.nifSocioTutor = nifSocioTutor;
    }

    public Long getIdInfantil() {
        return idInfantil;
    }

    public void setIdInfantil(Long idSocio) {
        this.idInfantil = idSocio;
    }

    public String getNifSocioTutor() {
        return nifSocioTutor;
    }

    public void setNifSocioTutor(String nifSocioTutor) {
        this.nifSocioTutor = nifSocioTutor;
    }

    @Override
    public String toString() {
        return "InfantilDTO{" + getNombreSocio()+", " + getIdInfantil() +
                ", nifSocioTutor='" + nifSocioTutor + '\'' +
                '}';
    }
    public Infantil toInfantil(Connection conexion) throws DAOException, SQLException {
        String nombre = super.getNombreSocio();
        String nif = super.getNif();
        MySQLDAOManager mySQLDAOManager = new MySQLDAOManager(conexion);
        List<SocioDTO> listaSocios = mySQLDAOManager.getSocioDAO().obtenerTodos();
        List<EstandarDTO> listaEstandar = mySQLDAOManager.getEstandarDAO().obtenerTodos();
        List<FederadoDTO> listaFederados = mySQLDAOManager.getFederadoDAO().obtenerTodos();
        Long idSocio;
        Estandar socioTutorNo=null;

        for (SocioDTO socioDTO : listaSocios){
            if (getNifSocioTutor().equals(socioDTO.getNif())){
                idSocio = socioDTO.getIdSocio();
                for (FederadoDTO federadoDTO : listaFederados){
                    if(Objects.equals(federadoDTO.getIdFederado(), idSocio)){
                        Federado socioTutor = federadoDTO.toFederado(conexion);
                        Infantil infantil = new Infantil(nombre,socioTutor);
                        infantil.setNumeroSocio(getIdInfantil());
                        return infantil;

                    }
                }
                for (EstandarDTO estandarDTO : listaEstandar){
                    if(Objects.equals(estandarDTO.getIdEstandar(), idSocio)){
                        Estandar socioTutor = estandarDTO.toEstandar(conexion);
                        Infantil infantil = new Infantil(nombre,socioTutor);
                        infantil.setNumeroSocio(getIdInfantil());
                        return infantil;
                    }
                }
            }
        }
        return new Infantil(nombre,socioTutorNo);
    }
    public String setNumeroSocio(Long idInfantil){
        String numeroSocio = String.format("SOC%04d",idInfantil);
        return numeroSocio;
    }
}*/
