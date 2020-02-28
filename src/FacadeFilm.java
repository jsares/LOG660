

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
import java.util.ArrayList;
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
	
	public ArrayList<ArrayList<String>> searchFilm(String searchTerm) {
		
		session.beginTransaction();
		Object[][] data = new Object[10][10];
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		
		List<Film> films = session
				.createSQLQuery("SELECT * FROM Film WHERE LOWER(Titre) = ?")
				.addEntity(Film.class)
				.setParameter(0, searchTerm.toLowerCase())
				.list();
		
		String text = "";
		int counter = 0;
		for(Film film : films) {
			ArrayList<String> innerData = new ArrayList<String>();
			innerData.add(film.getTitre());
			innerData.add(film.getAnneesortie().toString());
			innerData.add(film.getGenres().toArray()[0].toString());
			innerData.add(film.getDuree().toString());
			innerData.add(film.getLangueoriginale());
			rows.add(innerData);
		}
		
		session.getTransaction().commit();
		
		return rows;
	}
	
	public Object[][] getEmptyRow() {
		return new String[][] { 
				{"", "", "", "", ""}
		};
	}
	
	public void closeSession() {
		session.close();
		HibernateUtil.shutdown();
	}
}
