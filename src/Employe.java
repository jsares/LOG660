// default package
// Generated Feb 15, 2020 2:25:06 PM by Hibernate Tools 5.4.7.Final

import java.math.BigInteger;

/**
 * Employe generated by hbm2java
 */
public class Employe implements java.io.Serializable {

	private BigInteger matricule;
	private Client client;

	public Employe() {
	}

	public Employe(BigInteger matricule, Client client) {
		this.matricule = matricule;
		this.client = client;
	}

	public BigInteger getMatricule() {
		return this.matricule;
	}

	public void setMatricule(BigInteger matricule) {
		this.matricule = matricule;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}