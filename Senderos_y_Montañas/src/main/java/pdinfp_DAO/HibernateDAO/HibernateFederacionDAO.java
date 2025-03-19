package pdinfp_DAO.HibernateDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.FederacionDAO;
import pdinfp_Entitys.FederacionEntity;
import pdinfp_util.ConectorHibernate;
import pdinfp_vista.BBDDVista;

import java.util.List;

public class HibernateFederacionDAO implements FederacionDAO {


    @Override
    public FederacionEntity insertar(FederacionEntity a) throws DAOException {
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
    public void modificar(FederacionEntity a) throws DAOException {
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
    public void eliminar(FederacionEntity a) throws DAOException {
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
    public List<FederacionEntity> obtenerTodos() throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            return session.createQuery("from FederacionEntity", FederacionEntity.class).list();
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public FederacionEntity obtener(Long id) throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            FederacionEntity federacion = session.get(FederacionEntity.class, id);
            if (federacion == null) {
                throw new DAOException(BBDDVista.tareaFail());
            }
            return federacion;
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }
}

