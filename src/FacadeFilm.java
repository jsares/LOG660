

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.hibernate.Session;

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
	
	public String searchFilm(String searchTerm) {
		
		session.beginTransaction();
		List<Film> rows = session.createSQLQuery("SELECT * FROM Film WHERE Titre = ?").addEntity(Film.class).setParameter(0, "King Kong").list();
		
		String text = "";
		for(Film film : rows) {
			text += film.getTitre() + "\n";
		}
		
		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
		
		return text;
		
	}
}
