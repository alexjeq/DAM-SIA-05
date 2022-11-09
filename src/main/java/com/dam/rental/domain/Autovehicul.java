package com.dam.rental.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Autovehicul implements Comparable<Autovehicul>, Serializable {
	private static final long serialVersionUID = 1L;

	private Integer VehiculId;
	
	private String Model;
	
	private String NumarInmatriculare;
	
	private String SerieSasiu;
	
	private Integer AnInmatriculare;
	
	private Integer AnProductie;
	
	private Integer CapacitateCilindrica;
	
	private Date DataInmatriculare;
	
	private String Culoare;
	
	private String Descriere;
	
	private String NrPasageri;
	
	private Integer CostInchiriere;
	
	private String CategorieAdmisa;
	
	private List<Inchiriere> inchirieri = new ArrayList<>();
	private Inchiriere inchiriereaCRT;
	
	private List<Review> reviewuri = new ArrayList<>();
	
	protected Combustibil combustibil = Combustibil.Benzina;
	
	public Integer getVehiculId() {
		return VehiculId;
	}
	public void setVehiculId(Integer vehiculId) {
		VehiculId = vehiculId;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getNumarInmatriculare() {
		return NumarInmatriculare;
	}
	public void setNumarInmatriculare(String numarInmatriculare) {
		NumarInmatriculare = numarInmatriculare;
	}
	public String getSerieSasiu() {
		return SerieSasiu;
	}
	public void setSerieSasiu(String serieSasiu) {
		SerieSasiu = serieSasiu;
	}
	public Integer getAnInmatriculare() {
		return AnInmatriculare;
	}
	public void setAnInmatriculare(Integer anInmatriculare) {
		AnInmatriculare = anInmatriculare;
	}
	public Integer getCapacitateCilindrica() {
		return CapacitateCilindrica;
	}
	public void setCapacitateCilindrica(Integer capacitateCilindrica) {
		CapacitateCilindrica = capacitateCilindrica;
	}
	public Date getDataInmatriculare() {
		return DataInmatriculare;
	}
	public void setDataInmatriculare(Date dataInmatriculare) {
		DataInmatriculare = dataInmatriculare;
	}
	public String getCuloare() {
		return Culoare;
	}
	public void setCuloare(String culoare) {
		Culoare = culoare;
	}
	public String getDescriere() {
		return Descriere;
	}
	public void setDescriere(String descriere) {
		Descriere = descriere;
	}
	public String getNrPasageri() {
		return NrPasageri;
	}
	public void setNrPasageri(String nrPasageri) {
		NrPasageri = nrPasageri;
	}
	public Integer getCostInchiriere() {
		return CostInchiriere;
	}
	public void setCostInchiriere(Integer costInchiriere) {
		CostInchiriere = costInchiriere;
	}
	public String getCategorieAdmisa() {
		return CategorieAdmisa;
	}
	public void setCategorieAdmisa(String categorieAdmisa) {
		CategorieAdmisa = categorieAdmisa;
	}
	public List<Inchiriere> getInchirieri() {
		return inchirieri;
	}
	public void setInchirieri(List<Inchiriere> inchirieri) {
		this.inchirieri = inchirieri;
	}
	public Inchiriere getInchiriereaCRT() {
		return inchiriereaCRT;
	}
	public void setInchiriereaCRT(Inchiriere inchiriereaCRT) {
		this.inchiriereaCRT = inchiriereaCRT;
	}
	public List<Review> getReviewuri() {
		return reviewuri;
	}
	public void setReviewuri(List<Review> reviewuri) {
		this.reviewuri = reviewuri;
	}
	public Combustibil getCombustibil() {
		return combustibil;
	}
	public void setCombustibil(Combustibil combustibil) {
		this.combustibil = combustibil;
	}
	
	
	
	
	public Integer getAnProductie() {
		return AnProductie;
	}
	public void setAnProductie(Integer anProductie) {
		AnProductie = anProductie;
	}
	
	
	@Override
	public String toString() {
		return "Autovehicul [VehiculId=" + VehiculId + ", Model=" + Model + ", NumarInmatriculare=" + NumarInmatriculare
				+ ", SerieSasiu=" + SerieSasiu + ", AnInmatriculare=" + AnInmatriculare + ", AnProductie=" + AnProductie
				+ ", CapacitateCilindrica=" + CapacitateCilindrica + ", DataInmatriculare=" + DataInmatriculare
				+ ", Culoare=" + Culoare + ", Descriere=" + Descriere + ", NrPasageri=" + NrPasageri
				+ ", CostInchiriere=" + CostInchiriere + ", CategorieAdmisa=" + CategorieAdmisa + ", combustibil="
				+ combustibil + "]";
	}
	
	
	
	public Autovehicul() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Autovehicul(Integer vehiculId, String model, String numarInmatriculare, String serieSasiu,
			Integer anInmatriculare, Integer anProductie , Integer capacitateCilindrica, Date dataInmatriculare, String culoare,
			String descriere, String nrPasageri, Integer costInchiriere, String categorieAdmisa,
			List<Inchiriere> inchirieri, Inchiriere inchiriereaCRT, List<Review> reviewuri, Combustibil combustibil) {
		super();
		VehiculId = vehiculId;
		Model = model;
		NumarInmatriculare = numarInmatriculare;
		SerieSasiu = serieSasiu;
		AnInmatriculare = anInmatriculare;
		AnProductie = anProductie;
		CapacitateCilindrica = capacitateCilindrica;
		DataInmatriculare = dataInmatriculare;
		Culoare = culoare;
		Descriere = descriere;
		NrPasageri = nrPasageri;
		CostInchiriere = costInchiriere;
		CategorieAdmisa = categorieAdmisa;
		this.inchirieri = inchirieri;
		this.inchiriereaCRT = inchiriereaCRT;
		this.reviewuri = reviewuri;
		this.combustibil = combustibil;
	}
	
	
	
	public Autovehicul(Integer vehiculId, String model, String numarInmatriculare, String serieSasiu,
			Integer anInmatriculare, Integer capacitateCilindrica, Date dataInmatriculare, String culoare,
			String nrPasageri, Integer costInchiriere, String categorieAdmisa, List<Inchiriere> inchirieri,
			Combustibil combustibil) {
		super();
		VehiculId = vehiculId;
		Model = model;
		NumarInmatriculare = numarInmatriculare;
		SerieSasiu = serieSasiu;
		AnInmatriculare = anInmatriculare;
		CapacitateCilindrica = capacitateCilindrica;
		DataInmatriculare = dataInmatriculare;
		Culoare = culoare;
		NrPasageri = nrPasageri;
		CostInchiriere = costInchiriere;
		CategorieAdmisa = categorieAdmisa;
		this.inchirieri = inchirieri;
		this.combustibil = combustibil;
	}
	
	
	
	public Autovehicul(Integer vehiculId, String model) {
		super();
		VehiculId = vehiculId;
		Model = model;
	}
	
	
	
	public Autovehicul(Integer vehiculId, String model, String numarInmatriculare, String serieSasiu,
			Integer capacitateCilindrica, String culoare, String descriere, String nrPasageri,
			Combustibil combustibil) {
		super();
		VehiculId = vehiculId;
		Model = model;
		NumarInmatriculare = numarInmatriculare;
		SerieSasiu = serieSasiu;
		CapacitateCilindrica = capacitateCilindrica;
		Culoare = culoare;
		Descriere = descriere;
		NrPasageri = nrPasageri;
		this.combustibil = combustibil;
	}
	
	public int compareTo(Integer anotherInteger) {
		return AnInmatriculare.compareTo(anotherInteger);
	}
	@Override
	public int compareTo(Autovehicul a) {
		if (this.equals(a))
			return 0;
		return this.getModel().compareTo(a.getModel());
	}

}

enum Combustibil{
	Benzina, Motorina, Gaz, Electrica
}

