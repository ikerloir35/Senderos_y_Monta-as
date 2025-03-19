package pdinfp_util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pdinfp_vista.BBDDVista;

//Conector para Hibernate con sus métodos de creación, obtención y cierre.
//Sustituye al de JDBC
public class ConectorHibernate {

        private static final SessionFactory sessionFactory = buildSessionFactory();

        private static SessionFactory buildSessionFactory() {
            try {
                // Crea la sessionFactory con la configuración de hibernate.cfg.xml
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

                return sessionFactory;

            }
            catch (Throwable ex) {
                // Si falla nos manda un error
                BBDDVista.conexionNo();
                throw new ExceptionInInitializerError(ex);
            }
        }

        //Método para obtener la sessionFactory
        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

        //Método para cerrar la sessionFactory
        public static void shutdown() {

            getSessionFactory().close();
        }


}
