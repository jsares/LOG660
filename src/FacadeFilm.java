

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
	
	public String[] search(String title, String[] year, String countryProd, String lang, String genre, String realisateur, String acteur) {
		if(title.isEmpty() 
				&& (year[0].isEmpty() && year[1].isEmpty()) 
				&& countryProd.isEmpty()
				&& lang.isEmpty()
				&& genre.isEmpty()
				&& realisateur.isEmpty()
				&& acteur.isEmpty())
			return this.getEmptyRow();
		
		Query qry = this.buildSearchQuery(title, year, countryProd, lang, genre, realisateur, acteur);
		
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
	
	private Query buildSearchQuery(String title, String[] year, String countryProd, String lang, String genre, String realisateur, String acteur) {
		
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
		
		if(!lang.isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
		
			qryText += "LOWER(LangueOriginale) LIKE(?)";
		}
		
		if(!genre.isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
		
			qryText += "LOWER(genre) LIKE(?)";
		}
		
		if(!realisateur.isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
		
			qryText += "LOWER(personneRealisateur.nom) LIKE(?)";
		}
		
		if(!acteur.isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
		
			qryText += "LOWER(personneActeur.nom) LIKE(?)";
		}
	
		Query query = session.createSQLQuery(this.getBaseQuery(countryProd, genre, realisateur, acteur) + qryText).addEntity(Film.class);
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
		
		if(!lang.isEmpty()) {
			query.setParameter(counter, "%"+lang.toLowerCase()+"%");
			counter++;
		}
		
		if(!genre.isEmpty()) {
			query.setParameter(counter, "%"+genre.toLowerCase()+"%");
			counter++;
		}
		
		if(!realisateur.isEmpty()) {
			query.setParameter(counter, "%"+realisateur.toLowerCase()+"%");
			counter++;
		}
		
		if(!acteur.isEmpty()) {
			query.setParameter(counter, "%"+acteur.toLowerCase()+"%");
		}
		
		return query;
	}
	
	private String getBaseQuery(String countryProd, String genre, String realisateur, String acteur) {
		String result = "SELECT * FROM Film f ";
		
		if(!countryProd.isEmpty()) {
			result += "JOIN filmpaysproduction fp ON fp.idFilm = f.idFilm" + 
					"  JOIN paysProduction p ON p.idPaysProduction = fp.idPaysProduction";
		}
		
		if(!countryProd.isEmpty()) {
			result += " JOIN filmGenre fg ON fg.idfilm = f.idFilm" + 
					"   JOIN genre g ON g.idgenre = fg.idgenre";
		}
		
		result += "   JOIN personnage ON personnage.idfilm = f.idfilm";
		
		if(!realisateur.isEmpty()) 
		{
			result += " JOIN realisateur r ON r.idrealisateur = f.idrealisateur" +
			"           JOIN personne personneRealisateur ON personneRealisateur.idpersonne = r.idpersonne";
		}
		
		if(!acteur.isEmpty()) {
			result += " JOIN personne personneActeur ON personneActeur.idpersonne = personnage.idpersonne";
		}
		
		return result + " WHERE ";
	}
}
