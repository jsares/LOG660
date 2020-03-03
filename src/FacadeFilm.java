

import java.awt.EventQueue;

import javax.persistence.PersistenceException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.hibernate.Query;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;

import org.hibernate.exception.GenericJDBCException;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.math.BigInteger;

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
		return new String[] {"Aucun film ne correspond � votre recherche."};
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

	/**
	 * Fonction pour effectuer une location de film
	 * Pour le plugguer au GUI, mettre idClient et idFilm en paramètre de la fonction et afficher les messages d'erreur du switch casedans le GUI
	 */
	public void locationFilm(){
		session.beginTransaction();
		//Devrons être en paramètres de la fct lors d'integration avec
		int idClient=55666;
		int idFilm=107688;


		try {
			Query q = session.createSQLQuery(" { call newlocation(?,?) }");
			q.setInteger(1,idClient);  // first parameter, index starts with 0
			q.setInteger(2,idFilm); // secon parameter
			q.executeUpdate();
		}catch(PersistenceException e) {
			if (e.getCause() != null && e.getCause().getCause() != null && e.getCause().getCause() instanceof SQLException) {

				SQLException se = (SQLException) e.getCause().getCause();

				switch (se.getErrorCode()) {
					case 20001:
						//Code pour afficher message à l'utilisateur
						System.out.println("Vous devez avoir 18 ans et plus pour louer un film");
						break;
					case 20002:
						//Code pour afficher message à l'utilisateur
						System.out.println("Ce film n'est pas en inventaire");
						break;
					case 20003:
						//Code pour afficher message à l'utilisateur
						System.out.println("Vous avez atteint le nombre maximal de location pour votre forfait");
						break;

				}


			}
		}

		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();

	}
	//Sert pu à rien mais on le garde au cas
	public void populerCopieFIlm(){
		session.beginTransaction();

		String sqlClient = "SELECT * FROM Film";
		List<Film> rows = session.createSQLQuery(sqlClient).addEntity(Film.class).list();
		int nextVal;
		for(int i=0;i<rows.size();i++){

			Copiefilm copie=new Copiefilm(BigInteger.valueOf(i*10+1),rows.get(i),true);
			session.save(copie);
			copie=new Copiefilm(BigInteger.valueOf(i*10+2),rows.get(i),true);
			session.save(copie);
			copie=new Copiefilm(BigInteger.valueOf(i*10+3),rows.get(i),true);
			session.save(copie);
		}


		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
	}

}
