
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session; 

public class Demo {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		// l'enonce "FORM Client" est en langage HQL
		//List<ClientTest> rows = session.createSQLQuery("SELECT * FROM ClientTest").list(); 
		
		//ClientTest result = rows.get(0);
		
		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();

		System.out.println("Session terminee");
		
		
		
	}
}