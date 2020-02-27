

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Rectangle;


// default package
// Generated Feb 15, 2020 2:25:06 PM by Hibernate Tools 5.4.7.Final


public class FacadeFilm  {
	private Session session = null;

	public FacadeFilm() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public boolean connectClient(String email, String password) {
		String query = "FROM Client WHERE LOWER(courriel) = :email AND motdepasse = :password";
		Query q = session.createQuery(query);
		q.setParameter("email", email.toLowerCase());
		q.setParameter("password", password);
		List<Client> client = q.list();//.get(0);
		return client.size()>=1;
	}
	
	public String searchFilm(String searchTerm) {
		session.beginTransaction();
		List<Film> rows = session.createQuery("FROM Film WHERE ROWNUM <= 10").list(); 
		
		String text = "";
		for(Film film : rows) {
			text += film.getTitre() + "\n";
		}
		
		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
		
		return text;
	}

	public static void main(String args[]) {
		FacadeFilm f = new FacadeFilm();
		f.connectClient("ArnoldLWixom70@gmail.com", "ahng7Rooh2lo");
	}
}
