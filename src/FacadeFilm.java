

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
	private SearchItems searchItems = null;

	public FacadeFilm() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public String[] search(String title, String[] year, String countryProd, String lang, String genre, String realisateur, String acteur) {
		searchItems = new SearchItems(title, year, countryProd, lang, genre, realisateur, acteur);
		
		if(searchItems.isAllEmpty())
			return this.getEmptyRow();
		
		Query qry = this.buildSearchQuery();
		
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
	
	public SearchItems handleRowClick(String rowContent) {
		SearchItems items = new SearchItems();
		items.setTitle(rowContent.substring(0, rowContent.length()-6).trim());
		items.setYear(new String[] {rowContent.substring(rowContent.length()-6).trim().substring(1,5)});
		
		return items;
	}
	
	private Query buildSearchQuery() {
		
		String qryText = "";
		
		if(!searchItems.getTitle().isEmpty()) qryText += "LOWER(Titre) LIKE(:title)";
		
		String from = searchItems.getYear()[0].isEmpty() ? null : searchItems.getYear()[0];
		String to = searchItems.getYear()[1].isEmpty() ? null : searchItems.getYear()[1];
		
		if((from != null || to != null) && !qryText.isEmpty()) 
			qryText += " AND ";
		
		if(from != null && to != null)
			qryText += "anneesortie BETWEEN :from AND :to";
		else if (from != null && to == null)
			qryText += "anneesortie BETWEEN :from AND 999999";
		else if (from == null && to != null)
			qryText += "anneesortie BETWEEN 0 AND :to";
		
		if(!searchItems.getCountryProd().isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
			
			qryText += "LOWER(p.pays) LIKE(:countryProd)";
		}
		
		if(!searchItems.getLang().isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
		
			qryText += "LOWER(LangueOriginale) LIKE(:lang)";
		}
		
		if(!searchItems.getGenre().isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
		
			qryText += " f.idfilm IN (" + 
					"SELECT fg.idFIlm FROM filmgenre fg JOIN genre g ON g.idGenre = fg.idGenre WHERE LOWER(genre) LIKE(:genre)" + 
					") ";
		}
		
		if(!searchItems.getRealisateur().isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
		
			qryText += " f.idrealisateur IN (" + 
					"SELECT r.idrealisateur FROM personne p JOIN realisateur r ON p.idpersonne = r.idpersonne WHERE LOWER(p.nom) LIKE(:realisateur)" + 
					") ";
		}
		
		if(!searchItems.getActeur().isEmpty()) {
			if(!qryText.isEmpty()) qryText += " AND ";
		
			qryText += " f.idfilm IN (" + 
					"SELECT personnage.idfilm FROM personne personneActeur JOIN personnage ON personneActeur.idpersonne = personnage.idpersonne WHERE LOWER(personneActeur.nom) LIKE(:acteur)" + 
					") ";
		}
		
		return this.getSearcParams(qryText);		
	}
	
	private Query getSearcParams(String qryText) {
		Query query = session.createSQLQuery(this.getBaseSearchQuery() + qryText + " ORDER BY f.titre").addEntity(Film.class);
		
		if(!searchItems.getTitle().isEmpty())
			query.setParameter("title", "%"+searchItems.getTitle().toLowerCase()+"%");
		
		if(!searchItems.getYear()[0].isEmpty())
			query.setParameter("from", searchItems.getYear()[0]);
		
		if(!searchItems.getYear()[1].isEmpty())
			query.setParameter("to", searchItems.getYear()[1]);

		if(!searchItems.getCountryProd().isEmpty())
			query.setParameter("countryProd", "%"+searchItems.getCountryProd().toLowerCase()+"%");
		
		if(!searchItems.getLang().isEmpty())
			query.setParameter("lang", "%"+searchItems.getLang().toLowerCase()+"%");
		
		if(!searchItems.getGenre().isEmpty())
			query.setParameter("genre", "%"+searchItems.getGenre().toLowerCase()+"%");
		
		if(!searchItems.getRealisateur().isEmpty())
			query.setParameter("realisateur", "%"+searchItems.getRealisateur().toLowerCase()+"%");
		
		if(!searchItems.getActeur().isEmpty())
			query.setParameter("acteur", "%"+searchItems.getActeur().toLowerCase()+"%");
		
		return query;
	}
	
	private String getBaseSearchQuery() {
		String result = "SELECT * FROM Film f ";
		
		if(!searchItems.getCountryProd().isEmpty()) {
			result += "JOIN filmpaysproduction fp ON fp.idFilm = f.idFilm" + 
					"  JOIN paysProduction p ON p.idPaysProduction = fp.idPaysProduction";
		}
		
		return result + " WHERE ";
	}

	public boolean connectClient(String email, String password) {
		String query = "FROM Client WHERE LOWER(courriel) = :email AND motdepasse = :password";
		Query q = session.createQuery(query);
		q.setParameter("email", email.toLowerCase());
		q.setParameter("password", password);
		List<Client> client = q.list();//.get(0);
		return client.size()>=1;
	}
}
