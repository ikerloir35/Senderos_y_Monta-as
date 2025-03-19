package pdinfp_DAO.HibernateDAO;

import pdinfp_DAO.controladorDAO.*;

import java.sql.SQLException;

public class HibernateDAOManager implements DAOManager{


    private SocioDAO socioDAO = null;
    private SeguroDAO seguroDAO = null;
    private InscripcionDAO inscripcionDAO = null;
    private InfantilDAO infantilDAO=null;
    private FederadoDAO federadoDAO =null;
    private FederacionDAO federacionDAO=null;
    private ExcursionDAO excursionDAO= null;
    private EstandarDAO estandarDAO = null;




    public HibernateDAOManager() throws SQLException{

    }

    @Override
    public SocioDAO getSocioDAO() {
        if (socioDAO == null){
            socioDAO = new HibernateSocioDAO();
        }
        return  socioDAO;
    }

    @Override
    public SeguroDAO getSeguroDAO() {
        if (seguroDAO == null){
            seguroDAO = new HibernateSeguroDAO();
        }
        return  seguroDAO;
    }

    @Override
    public InscripcionDAO getInscripcionDAO() {
        if (inscripcionDAO == null){
            inscripcionDAO = new HibernateInscripcionDAO();
        }
        return  inscripcionDAO;
    }

    @Override
    public InfantilDAO getInfantilDAO() {
        if (infantilDAO == null){
            infantilDAO= new HibernateInfantilDAO();
        }
        return  infantilDAO;
    }

    @Override
    public FederadoDAO getFederadoDAO() {
        if (federadoDAO == null){
            federadoDAO = new HibernateFederadoDAO();
        }
        return  federadoDAO;
    }

    @Override
    public FederacionDAO getFederacionDAO() {
        if (federacionDAO == null){
            federacionDAO = new HibernateFederacionDAO();
        }
        return  federacionDAO;
    }

    @Override
    public ExcursionDAO getExcursionDAO() {
        if (excursionDAO == null){
            excursionDAO = new HibernateExcursionDAO();
        }
        return  excursionDAO;
    }

    @Override
    public EstandarDAO getEstandarDAO() {
        if (estandarDAO == null){
            estandarDAO = new HibernateEstandarDAO();
        }
        return  estandarDAO;
    }
}
