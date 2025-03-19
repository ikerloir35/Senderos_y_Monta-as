package pdinfp_DAO.controladorDAO;

import java.util.List;

public interface DAO<T,K> {

    /*En esta clase generica T = clase especifica a usar (e.g. EstandarDAO), y k = tipo de datos
    de la clase primaria*/

    T insertar(T a) throws DAOException;
    void modificar(T a) throws DAOException;
    void eliminar(T a) throws DAOException;
    List<T> obtenerTodos()throws DAOException;
    T obtener(K id)throws DAOException;

}
