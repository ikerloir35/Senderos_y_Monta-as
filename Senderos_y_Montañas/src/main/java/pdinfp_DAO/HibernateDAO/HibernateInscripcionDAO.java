package pdinfp_DAO.HibernateDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.InscripcionDAO;
import pdinfp_Entitys.InscripcionEntity;
import pdinfp_util.ConectorHibernate;
import pdinfp_vista.BBDDVista;

import java.util.List;

public class HibernateInscripcionDAO implements InscripcionDAO {

    @Override
    public InscripcionEntity insertar(InscripcionEntity a) throws DAOException {
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
    public void modificar(InscripcionEntity a) throws DAOException {
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
    public void eliminar(InscripcionEntity a) throws DAOException {
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
    public List<InscripcionEntity> obtenerTodos() throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            return session.createQuery("from InscripcionEntity", InscripcionEntity.class).list();
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public InscripcionEntity obtener(Long id) throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {

            InscripcionEntity inscripcion = session.get(InscripcionEntity.class, id);
            if (inscripcion == null) {
                throw new DAOException(BBDDVista.tareaFail());
            }
            return inscripcion;
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    public List<InscripcionEntity> obtenerPorExcursion(Long idExcursion) throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {

            return session.createQuery("from InscripcionEntity where excursiones_idExcursion = :idExcursion", InscripcionEntity.class)
                    .setParameter("idExcursion", idExcursion)
                    .list();

        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }
}