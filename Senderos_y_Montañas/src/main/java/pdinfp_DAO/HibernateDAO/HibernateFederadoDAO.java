package pdinfp_DAO.HibernateDAO;


import org.hibernate.Session;
import org.hibernate.Transaction;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.FederadoDAO;
import pdinfp_Entitys.FederadoEntity;
import pdinfp_util.ConectorHibernate;
import pdinfp_vista.BBDDVista;

import java.util.List;

public class HibernateFederadoDAO implements FederadoDAO {

    @Override
    public FederadoEntity insertar(FederadoEntity a) throws DAOException {
        Transaction tx = null;
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.persist(a);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
        return a;
    }

    @Override
    public void modificar(FederadoEntity a) throws DAOException {
        Transaction tx = null;
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.update(a);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public void eliminar(FederadoEntity a) throws DAOException {
        Transaction tx = null;
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.delete(a);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public List<FederadoEntity> obtenerTodos() throws DAOException {

        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            return session.createQuery("from FederadoEntity", FederadoEntity.class).list();

        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public FederadoEntity obtener(Long id) throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            FederadoEntity federado = session.get(FederadoEntity.class, id);
            if (federado == null) {
                throw new DAOException(BBDDVista.tareaFail());
            }
            return federado;

        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }
}