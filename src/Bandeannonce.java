// default package
// Generated Feb 15, 2020 2:25:06 PM by Hibernate Tools 5.4.7.Final

import java.math.BigInteger;

/**
 * Bandeannonce generated by hbm2java
 */
public class Bandeannonce implements java.io.Serializable {

	private BigInteger idbandeannonce;
	private Film film;
	private String lien;

	public Bandeannonce() {
	}

	public Bandeannonce(BigInteger idbandeannonce, Film film, String lien) {
		this.idbandeannonce = idbandeannonce;
		this.film = film;
		this.lien = lien;
	}

	public BigInteger getIdbandeannonce() {
		return this.idbandeannonce;
	}

	public void setIdbandeannonce(BigInteger idbandeannonce) {
		this.idbandeannonce = idbandeannonce;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String getLien() {
		return this.lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

}
