

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.hibernate.Query;
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
	
	public String[] search(String title, String[] year, String countryProd) {
		if(title.isEmpty() && (year[0].isEmpty() && year[1].isEmpty()) && countryProd.isEmpty()) return this.getEmptyRow();
		
		Query qry = this.buildSearchQuery(title, year, countryProd);
		
		session.beginTransaction();

		List<Film> films = qry.list();
		
		session.getTransaction().commit();
		
		if(films.size() == 0) return this.getNoDataFoundMessage();
		
		//Prepare data
		String[] data = new String[films.size()];
		int counter = 0;
		for(Film film : films) {
			data[counter] = film.getTitre() + " (" + film.getAnneesortie().toString() +")";
			counter++;
		}
		
		return data;
	}
	
	public String[] getEmptyRow() {
		return new String[] {" "};
	}
	
	public String[] getNoDataFoundMessage() {
		return new String[] {"Aucun film ne correspond à votre recherche."};
	}
	
	public void closeSession() {
		session.close();
		HibernateUtil.shutdown();
	}
	
	private Query buildSearchQuery(String title, String[] year, String countryProd) {
		
		String qryText = "";
		
		if(!title.isEmpty()) qryText += "LOWER(Titre) LIKE(?)";
		
		String from = year[0].isEmpty() ? null : year[0];
		String to = year[1].isEmpty() ? null : year[1];
		
		if((from != null || to != null) && !qryText.isEmpty()) 
			qryText += " AND ";
		
		if(from != null && to != null)
			qryText += "anneesortie BETWEEN ? AND ?";
		else if (from != null && to == null)
			qryText += "anneesortie BETWEEN ? AND 999999";
		else if (from == null && to != null)
			qryText += "anneesortie BETWEEN 0 AND ?";
		
		if(!countryProd.isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
			
			qryText += "LOWER(p.pays) LIKE(?)";
		}
	
		Query query = session.createSQLQuery(this.getBaseQuery() + qryText).addEntity(Film.class);
		int counter = 0;
		
		if(!title.isEmpty()) {
			query.setParameter(counter, "%"+title.toLowerCase()+"%");
			counter++;
		}
		
		if(!year[0].isEmpty()) {
			query.setParameter(counter, year[0]);
			counter++;
		}
		
		if(!year[1].isEmpty()) {
			query.setParameter(counter, year[1]);
			counter++;
		}
		
		if(!countryProd.isEmpty()) {
			query.setParameter(counter, "%"+countryProd.toLowerCase()+"%");
			counter++;
		}
		
		return query;
	}
	
	private String getBaseQuery() {
		return "SELECT * FROM Film f" +
		" JOIN filmpaysproduction fp ON fp.idFilm = f.idFilm " + 
		" JOIN paysProduction p ON p.idPaysProduction = fp.idPaysProduction WHERE ";
	}
}
