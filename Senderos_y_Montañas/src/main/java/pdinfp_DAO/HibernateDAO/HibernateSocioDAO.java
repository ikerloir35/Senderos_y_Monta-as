package pdinfp_DAO.HibernateDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pdinfp_DAO.controladorDAO.DAOException;
import pdinfp_DAO.controladorDAO.SocioDAO;
import pdinfp_Entitys.SocioEntity;
import pdinfp_util.ConectorHibernate;
import pdinfp_vista.BBDDVista;

import java.util.List;

public class HibernateSocioDAO implements SocioDAO {

    @Override
    public SocioEntity insertar(SocioEntity a) throws DAOException {
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
    public void modificar(SocioEntity a) throws DAOException {
        Transaction tx = null;
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(a);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public void eliminar(SocioEntity a) throws DAOException {
        Transaction tx = null;
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            a.setActivo((byte) 0);
            session.update(a);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public List<SocioEntity> obtenerTodos() throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            return session.createQuery("from SocioEntity", SocioEntity.class).list();
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }

    @Override
    public SocioEntity obtener(Long id) throws DAOException {
        try (Session session = ConectorHibernate.getSessionFactory().openSession()) {
            SocioEntity socio = session.get(SocioEntity.class, id);
            if (socio == null) {
                throw new DAOException(BBDDVista.tareaFail());
            }
            return socio;
        } catch (Exception e) {
            throw new DAOException(BBDDVista.tareaFail(), e);
        }
    }
}