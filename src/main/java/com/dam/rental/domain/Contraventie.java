package com.dam.rental.domain;

import java.io.Serializable;

public class Contraventie implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ContraventieId;
	
	private Integer ValoareContraventie;
	
	private String Descriere;
	
	private Inchiriere inchiriere;

	public Integer getContraventieId() {
		return ContraventieId;
	}

	public void setContraventieId(Integer contraventieId) {
		ContraventieId = contraventieId;
	}

	public Integer getValoareContraventie() {
		return ValoareContraventie;
	}

	public void setValoareContraventie(Integer valoareContraventie) {
		ValoareContraventie = valoareContraventie;
	}

	public String getDescriere() {
		return Descriere;
	}

	public void setDescriere(String descriere) {
		Descriere = descriere;
	}

	public Inchiriere getInchiriere() {
		return inchiriere;
	}

	public void setInchiriere(Inchiriere inchiriere) {
		this.inchiriere = inchiriere;
	}

	@Override
	public String toString() {
		return "Contraventie [ContraventieId=" + ContraventieId + ", ValoareContraventie=" + ValoareContraventie
				+ ", Descriere=" + Descriere + ", inchiriere=" + inchiriere + "]";
	}

	public Contraventie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Contraventie(Integer contraventieId, Integer valoareContraventie, String descriere, Inchiriere inchiriere) {
		super();
		ContraventieId = contraventieId;
		ValoareContraventie = valoareContraventie;
		Descriere = descriere;
		this.inchiriere = inchiriere;
	}

	public Contraventie(Integer contraventieId, Integer valoareContraventie, Inchiriere inchiriere) {
		super();
		ContraventieId = contraventieId;
		ValoareContraventie = valoareContraventie;
		this.inchiriere = inchiriere;
	}
	
}
