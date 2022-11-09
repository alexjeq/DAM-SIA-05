package com.dam.rental.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;

public class Permis implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer PermisID;
	
	private Date DataEmitere;
	
	@Future(message = "Data expirării trebuie să fie în viitor.")
	private Date DataExpirare;
	
	protected Categorie categoriePermis = Categorie.B;

	public Integer getPermisID() {
		return PermisID;
	}

	public void setPermisID(Integer permisID) {
		PermisID = permisID;
	}

	public Date getDataEmitere() {
		return DataEmitere;
	}

	public void setDataEmitere(Date dataEmitere) {
		DataEmitere = dataEmitere;
	}

	public Date getDataExpirare() {
		return DataExpirare;
	}

	public void setDataExpirare(Date dataExpirare) {
		DataExpirare = dataExpirare;
	}

	public Categorie getCategoriePermis() {
		return categoriePermis;
	}

	public void setCategoriePermis(Categorie categoriePermis) {
		this.categoriePermis = categoriePermis;
	}

	public Permis() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permis(Integer permisID, Date dataEmitere,
			@Future(message = "Data expirării trebuie să fie în viitor.") Date dataExpirare,
			Categorie categoriePermis) {
		super();
		PermisID = permisID;
		DataEmitere = dataEmitere;
		DataExpirare = dataExpirare;
		this.categoriePermis = categoriePermis;
	}

	@Override
	public String toString() {
		return "Permis [PermisID=" + PermisID + ", DataEmitere=" + DataEmitere + ", DataExpirare=" + DataExpirare
				+ ", categoriePermis=" + categoriePermis + "]";
	}
	
}

enum Categorie{
	 B, C 
}
