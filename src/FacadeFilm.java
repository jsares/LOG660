

import java.awt.EventQueue;

import javax.persistence.PersistenceException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;

import org.hibernate.exception.GenericJDBCException;
import org.hibernate.query.*;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

	public FacadeFilm() {
		session = HibernateUtil.getSessionFactory().openSession();
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
