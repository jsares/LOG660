// default package
// Generated Feb 15, 2020 2:25:06 PM by Hibernate Tools 5.4.7.Final

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Client generated by hbm2java
 */
public class Client implements java.io.Serializable {

	private BigInteger idclient;
	private Forfait forfait;
	private String nomdefamille;
	private String prenom;
	private String courriel;
	private String numerotelephone;
	private String adresse;
	private String ville;
	private String province;
	private String codepostal;
	private Date datenaissance;
	private String motdepasse;
	private Set employes = new HashSet(0);
	private Set locationfilms = new HashSet(0);
	private Set cartecredits = new HashSet(0);

	public Client() {
	}

	public Client(BigInteger idclient, Forfait forfait, String nomdefamille, String prenom, String courriel,
			String numerotelephone, String adresse, String ville, String province, String codepostal,
			Date datenaissance, String motdepasse) {
		this.idclient = idclient;
		this.forfait = forfait;
		this.nomdefamille = nomdefamille;
		this.prenom = prenom;
		this.courriel = courriel;
		this.numerotelephone = numerotelephone;
		this.adresse = adresse;
		this.ville = ville;
		this.province = province;
		this.codepostal = codepostal;
		this.datenaissance = datenaissance;
		this.motdepasse = motdepasse;
	}

	public Client(BigInteger idclient, Forfait forfait, String nomdefamille, String prenom, String courriel,
			String numerotelephone, String adresse, String ville, String province, String codepostal,
			Date datenaissance, String motdepasse, Set employes, Set locationfilms, Set cartecredits) {
		this.idclient = idclient;
		this.forfait = forfait;
		this.nomdefamille = nomdefamille;
		this.prenom = prenom;
		this.courriel = courriel;
		this.numerotelephone = numerotelephone;
		this.adresse = adresse;
		this.ville = ville;
		this.province = province;
		this.codepostal = codepostal;
		this.datenaissance = datenaissance;
		this.motdepasse = motdepasse;
		this.employes = employes;
		this.locationfilms = locationfilms;
		this.cartecredits = cartecredits;
	}

	public BigInteger getIdclient() {
		return this.idclient;
	}

	public void setIdclient(BigInteger idclient) {
		this.idclient = idclient;
	}

	public Forfait getForfait() {
		return this.forfait;
	}

	public void setForfait(Forfait forfait) {
		this.forfait = forfait;
	}

	public String getNomdefamille() {
		return this.nomdefamille;
	}

	public void setNomdefamille(String nomdefamille) {
		this.nomdefamille = nomdefamille;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCourriel() {
		return this.courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getNumerotelephone() {
		return this.numerotelephone;
	}

	public void setNumerotelephone(String numerotelephone) {
		this.numerotelephone = numerotelephone;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCodepostal() {
		return this.codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public Date getDatenaissance() {
		return this.datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public String getMotdepasse() {
		return this.motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public Set getEmployes() {
		return this.employes;
	}

	public void setEmployes(Set employes) {
		this.employes = employes;
	}

	public Set getLocationfilms() {
		return this.locationfilms;
	}

	public void setLocationfilms(Set locationfilms) {
		this.locationfilms = locationfilms;
	}

	public Set getCartecredits() {
		return this.cartecredits;
	}

	public void setCartecredits(Set cartecredits) {
		this.cartecredits = cartecredits;
	}

}
