package com.dam.rental.domain;

import java.io.Serializable;
import java.util.Date;

public class Client implements Comparable<Client>, Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer ClientID;
	
	private String Nume;
	
	private String Adresa;
	
	private String NumarTelefon;
	
	private Date DataNasterii;
	
	private String CNP;
	
	private Permis permis;
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [ClientID=");
		builder.append(ClientID);
		builder.append(", Nume=");
		builder.append(Nume);
		builder.append(", Adresa=");
		builder.append(Adresa);
		builder.append(", NumarTelefon=");
		builder.append(NumarTelefon);
		builder.append(", DataNasterii=");
		builder.append(DataNasterii);
		builder.append(", CNP=");
		builder.append(CNP);
		builder.append(", permis=");
		builder.append(permis);
		builder.append("]");
		return builder.toString();
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Client(Integer clientID, String nume) {
		super();
		ClientID = clientID;
		Nume = nume;
	}

	public Client(Integer clientID, String nume, String adresa, String numarTelefon, Date dataNasterii, String cNP,
			Permis permis) {
		super();
		ClientID = clientID;
		Nume = nume;
		Adresa = adresa;
		NumarTelefon = numarTelefon;
		DataNasterii = dataNasterii;
		CNP = cNP;
		this.permis = permis;
	}

	public Client(Integer clientID, String nume, String adresa, String numarTelefon, Date dataNasterii, String cNP) {
		super();
		ClientID = clientID;
		Nume = nume;
		Adresa = adresa;
		NumarTelefon = numarTelefon;
		DataNasterii = dataNasterii;
		CNP = cNP;
	}	
	
	public Integer getClientID() {
		return ClientID;
	}

	public void setClientID(Integer clientID) {
		ClientID = clientID;
	}

	public String getNume() {
		return Nume;
	}

	public void setNume(String nume) {
		Nume = nume;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		Adresa = adresa;
	}

	public String getNumarTelefon() {
		return NumarTelefon;
	}

	public void setNumarTelefon(String numarTelefon) {
		NumarTelefon = numarTelefon;
	}

	public Date getDataNasterii() {
		return DataNasterii;
	}

	public void setDataNasterii(Date dataNasterii) {
		DataNasterii = dataNasterii;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

	public Permis getPermis() {
		return permis;
	}

	public void setPermis(Permis permis) {
		this.permis = permis;
	}
	
	@Override
	public int compareTo(Client c) {
		if (this.equals(c))
			return 0;
		return this.getNume().compareTo(c.getNume());
	}

}
