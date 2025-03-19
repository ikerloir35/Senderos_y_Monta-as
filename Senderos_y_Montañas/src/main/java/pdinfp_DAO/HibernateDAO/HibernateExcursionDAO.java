package pdinfp_DAO.HibernateDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.ExcursionDAO;
import pdinfp_Entitys.ExcursionEntity;
import pdinfp_util.ConectorHibernate;
import pdinfp_vista.BBDDVista;

import java.util.List;

public class HibernateExcursionDAO implements ExcursionDAO {

    @Override
    public ExcursionEntity insertar(ExcursionEntity a) throws DAOException {
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
    public void modificar(ExcursionEntity a) throws DAOException {
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
    public void eliminar(ExcursionEntity a) throws DAOException {
        Transaction transaction = null;
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.delete(a) ;
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public List<ExcursionEntity> obtenerTodos() throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            return session.createQuery("from ExcursionEntity", ExcursionEntity.class).list();
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public ExcursionEntity obtener(Long id) throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            ExcursionEntity excursion = session.get(ExcursionEntity.class, id);
            if (excursion == null) {
                throw new DAOException(BBDDVista.tareaFail());
            }
            return excursion;
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }
}
