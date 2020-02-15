import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	// Singleton
	private HibernateUtil() {}
	
	public static SessionFactory getSessionFactory() { 	return sessionFactory; }
	
	public static void shutdown() {
		// Ferme les antémémoires et les bassins (pool) de connexions
		getSessionFactory().close();
	}
}