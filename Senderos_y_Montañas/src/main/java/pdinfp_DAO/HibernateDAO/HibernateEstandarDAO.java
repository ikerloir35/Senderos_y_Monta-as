package pdinfp_DAO.HibernateDAO;


import org.hibernate.Session;
import org.hibernate.Transaction;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.EstandarDAO;
import pdinfp_Entitys.EstandarEntity;
import pdinfp_util.ConectorHibernate;
import pdinfp_vista.BBDDVista;

import java.util.List;



public class HibernateEstandarDAO implements EstandarDAO {

    @Override
    public EstandarEntity insertar(EstandarEntity a) throws DAOException {
        Transaction tx = null;
        try (Session session = ConectorHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.persist(a);
            tx.commit();

        }catch (Exception e){
            if (tx != null) {
                tx.rollback();
                throw new DAOException(BBDDVista.tareaFail());
            }
        }
        return a;
    }

    @Override
    public void modificar(EstandarEntity a) throws DAOException{
        Transaction tx = null;
        try  (Session session = ConectorHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.update(a);
            tx.commit();

        }catch (Exception e){
            if (tx != null) {
                tx.rollback();
                throw new DAOException(BBDDVista.tareaFail());
            }
        }

    }

    @Override
    public void eliminar(EstandarEntity a) throws DAOException{
        Transaction tx = null;
        try  (Session session = ConectorHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.delete(a);
            tx.commit();

        }catch (Exception e){
            if (tx != null) {
                tx.rollback();
                throw new DAOException(BBDDVista.tareaFail());
            }

        }
    }

    @Override
    public List<EstandarEntity> obtenerTodos() throws DAOException{
        try (Session session = ConectorHibernate.getSessionFactory().openSession()){
            return session.createQuery("from EstandarEntity", EstandarEntity.class).list();
        }
    }

    @Override
    public EstandarEntity obtener(Long id) throws DAOException{
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            EstandarEntity estandar = session.get(EstandarEntity.class, id);
            if (estandar == null) {
                throw new DAOException(BBDDVista.tareaFail());
            }
            return estandar;
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }

    }
}
