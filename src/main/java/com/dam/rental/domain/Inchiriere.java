package com.dam.rental.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inchiriere implements Comparable<Inchiriere>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer InchiriereId;
	
	private Integer DurataInchiriere;
	
	private Date DataInchiriereStart;
	
	private Date DataReturnare;
	
	private Client client;
	
	private Autovehicul autovehicul;
	
	private List<Contraventie> contraventii = new ArrayList<>();

	@Override
	public String toString() {
		return "Inchiriere [InchiriereId=" + InchiriereId + ", DurataInchiriere=" + DurataInchiriere
				+ ", DataInchiriereStart=" + DataInchiriereStart + ", DataReturnare=" + DataReturnare + ", client="
				+ client + ", autovehicul=" + autovehicul + ", contraventii=" + contraventii + "]";
	}

	public Integer getInchiriereId() {
		return InchiriereId;
	}

	public void setInchiriereId(Integer inchiriereId) {
		InchiriereId = inchiriereId;
	}

	public Integer getDurataInchiriere() {
		return DurataInchiriere;
	}

	public void setDurataInchiriere(Integer durataInchiriere) {
		DurataInchiriere = durataInchiriere;
	}

	public Date getDataInchiriereStart() {
		return DataInchiriereStart;
	}

	public void setDataInchiriereStart(Date dataInchiriereStart) {
		DataInchiriereStart = dataInchiriereStart;
	}

	public Date getDataReturnare() {
		return DataReturnare;
	}

	public void setDataReturnare(Date dataReturnare) {
		DataReturnare = dataReturnare;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Autovehicul getAutovehicul() {
		return autovehicul;
	}

	public void setAutovehicul(Autovehicul autovehicul) {
		this.autovehicul = autovehicul;
	}

	public List<Contraventie> getContraventii() {
		return contraventii;
	}

	public void setContraventii(List<Contraventie> contraventii) {
		this.contraventii = contraventii;
	}

	public Inchiriere(Integer inchiriereId, Integer durataInchiriere, Date dataInchiriereStart, Date dataReturnare,
			Client client, Autovehicul autovehicul, List<Contraventie> contraventii) {
		super();
		InchiriereId = inchiriereId;
		DurataInchiriere = durataInchiriere;
		DataInchiriereStart = dataInchiriereStart;
		DataReturnare = dataReturnare;
		this.client = client;
		this.autovehicul = autovehicul;
		this.contraventii = contraventii;
	}

	public Inchiriere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inchiriere(Integer inchiriereId, Integer durataInchiriere, Date dataInchiriereStart, Date dataReturnare) {
		super();
		InchiriereId = inchiriereId;
		DurataInchiriere = durataInchiriere;
		DataInchiriereStart = dataInchiriereStart;
		DataReturnare = dataReturnare;
	}

	public Inchiriere(Integer inchiriereId, Integer durataInchiriere, Date dataInchiriereStart) {
		super();
		InchiriereId = inchiriereId;
		DurataInchiriere = durataInchiriere;
		DataInchiriereStart = dataInchiriereStart;
	}

	public Inchiriere(Integer inchiriereId, Date dataInchiriereStart) {
		super();
		InchiriereId = inchiriereId;
		DataInchiriereStart = dataInchiriereStart;
	}


	@Override
	public int compareTo(Inchiriere i) {
		if (this.equals(i))
			return 0;
		return this.getDataInchiriereStart().compareTo(i.getDataInchiriereStart());
	}
	
	
 	

}
