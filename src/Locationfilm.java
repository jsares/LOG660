// default package
// Generated Feb 15, 2020 2:25:06 PM by Hibernate Tools 5.4.7.Final

import java.math.BigInteger;
import java.util.Date;

/**
 * Locationfilm generated by hbm2java
 */
public class Locationfilm implements java.io.Serializable {

	private BigInteger idlocation;
	private Client client;
	private Copiefilm copiefilm;
	private Date datelocation;

	public Locationfilm() {
	}

	public Locationfilm(BigInteger idlocation, Client client, Copiefilm copiefilm, Date datelocation) {
		this.idlocation = idlocation;
		this.client = client;
		this.copiefilm = copiefilm;
		this.datelocation = datelocation;
	}

	public BigInteger getIdlocation() {
		return this.idlocation;
	}

	public void setIdlocation(BigInteger idlocation) {
		this.idlocation = idlocation;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Copiefilm getCopiefilm() {
		return this.copiefilm;
	}

	public void setCopiefilm(Copiefilm copiefilm) {
		this.copiefilm = copiefilm;
	}

	public Date getDatelocation() {
		return this.datelocation;
	}

	public void setDatelocation(Date datelocation) {
		this.datelocation = datelocation;
	}

}
