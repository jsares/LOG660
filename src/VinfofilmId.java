// default package
// Generated Feb 15, 2020 2:25:06 PM by Hibernate Tools 5.4.7.Final

import java.math.BigInteger;

/**
 * VinfofilmId generated by hbm2java
 */
public class VinfofilmId implements java.io.Serializable {

	private String titre;
	private BigInteger anneesortie;
	private String langueoriginale;
	private BigInteger duree;
	private String resumescenario;
	private String nom;
	private String genre;

	public VinfofilmId() {
	}

	public VinfofilmId(String titre) {
		this.titre = titre;
	}

	public VinfofilmId(String titre, BigInteger anneesortie, String langueoriginale, BigInteger duree,
			String resumescenario, String nom, String genre) {
		this.titre = titre;
		this.anneesortie = anneesortie;
		this.langueoriginale = langueoriginale;
		this.duree = duree;
		this.resumescenario = resumescenario;
		this.nom = nom;
		this.genre = genre;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public BigInteger getAnneesortie() {
		return this.anneesortie;
	}

	public void setAnneesortie(BigInteger anneesortie) {
		this.anneesortie = anneesortie;
	}

	public String getLangueoriginale() {
		return this.langueoriginale;
	}

	public void setLangueoriginale(String langueoriginale) {
		this.langueoriginale = langueoriginale;
	}

	public BigInteger getDuree() {
		return this.duree;
	}

	public void setDuree(BigInteger duree) {
		this.duree = duree;
	}

	public String getResumescenario() {
		return this.resumescenario;
	}

	public void setResumescenario(String resumescenario) {
		this.resumescenario = resumescenario;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VinfofilmId))
			return false;
		VinfofilmId castOther = (VinfofilmId) other;

		return ((this.getTitre() == castOther.getTitre()) || (this.getTitre() != null && castOther.getTitre() != null
				&& this.getTitre().equals(castOther.getTitre())))
				&& ((this.getAnneesortie() == castOther.getAnneesortie())
						|| (this.getAnneesortie() != null && castOther.getAnneesortie() != null
								&& this.getAnneesortie().equals(castOther.getAnneesortie())))
				&& ((this.getLangueoriginale() == castOther.getLangueoriginale())
						|| (this.getLangueoriginale() != null && castOther.getLangueoriginale() != null
								&& this.getLangueoriginale().equals(castOther.getLangueoriginale())))
				&& ((this.getDuree() == castOther.getDuree()) || (this.getDuree() != null
						&& castOther.getDuree() != null && this.getDuree().equals(castOther.getDuree())))
				&& ((this.getResumescenario() == castOther.getResumescenario())
						|| (this.getResumescenario() != null && castOther.getResumescenario() != null
								&& this.getResumescenario().equals(castOther.getResumescenario())))
				&& ((this.getNom() == castOther.getNom()) || (this.getNom() != null && castOther.getNom() != null
						&& this.getNom().equals(castOther.getNom())))
				&& ((this.getGenre() == castOther.getGenre()) || (this.getGenre() != null
						&& castOther.getGenre() != null && this.getGenre().equals(castOther.getGenre())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTitre() == null ? 0 : this.getTitre().hashCode());
		result = 37 * result + (getAnneesortie() == null ? 0 : this.getAnneesortie().hashCode());
		result = 37 * result + (getLangueoriginale() == null ? 0 : this.getLangueoriginale().hashCode());
		result = 37 * result + (getDuree() == null ? 0 : this.getDuree().hashCode());
		result = 37 * result + (getResumescenario() == null ? 0 : this.getResumescenario().hashCode());
		result = 37 * result + (getNom() == null ? 0 : this.getNom().hashCode());
		result = 37 * result + (getGenre() == null ? 0 : this.getGenre().hashCode());
		return result;
	}

}