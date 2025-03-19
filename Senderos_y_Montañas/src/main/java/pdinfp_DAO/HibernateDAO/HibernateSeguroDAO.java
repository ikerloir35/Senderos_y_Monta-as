package pdinfp_DAO.HibernateDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.SeguroDAO;
import pdinfp_Entitys.SeguroEntity;
import pdinfp_util.ConectorHibernate;
import pdinfp_vista.BBDDVista;

import java.util.List;

public class HibernateSeguroDAO implements SeguroDAO {

    @Override
    public SeguroEntity insertar(SeguroEntity seguro) throws DAOException {
        Transaction tx = null;
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(seguro);
            tx.commit();


        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
        return seguro;
    }

    @Override
    public void modificar(SeguroEntity a) throws DAOException {
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
    public void eliminar(SeguroEntity a) throws DAOException {
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
    public List<SeguroEntity> obtenerTodos() throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {

            return session.createQuery("from SeguroEntity", SeguroEntity.class).list();

        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public SeguroEntity obtener(Long id) throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {

            SeguroEntity seguro = session.get(SeguroEntity.class, id);
            if (seguro == null) {
                throw new DAOException(BBDDVista.tareaFail());
            }
            return seguro;

        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }
}