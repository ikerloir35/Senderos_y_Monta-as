package pdinfp_DAO.HibernateDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.InfantilDAO;
import pdinfp_Entitys.InfantilEntity;
import pdinfp_util.ConectorHibernate;
import pdinfp_vista.BBDDVista;

import java.util.List;

public class HibernateInfantilDAO implements InfantilDAO {

    @Override
    public InfantilEntity insertar(InfantilEntity a) throws DAOException {
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
    public void modificar(InfantilEntity a) throws DAOException {
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
    public void eliminar(InfantilEntity a) throws DAOException {
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
    public List<InfantilEntity> obtenerTodos() throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            return session.createQuery("from InfantilEntity", InfantilEntity.class).list();
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public InfantilEntity obtener(Long id) throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            InfantilEntity infantil = session.get(InfantilEntity.class, id);
            if (infantil == null) {
                throw new DAOException(BBDDVista.tareaFail());
            }
            return infantil;
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }
}


