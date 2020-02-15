
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session; 

public class Main {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		// l'enonce "FORM Client" est en langage HQL
		List<Client> rows = session.createQuery("FROM Client WHERE ROWNUM <= 10").list(); 
		
		for(Client client : rows) {
			System.out.println(client.getNomdefamille());
		}
			
		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();

		System.out.println("Session terminee");
	}
}