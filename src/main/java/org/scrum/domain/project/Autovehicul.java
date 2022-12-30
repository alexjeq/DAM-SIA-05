package org.scrum.domain.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"serieSasiu", "nrImatriculare"}) //not to show sensitive data
@XmlRootElement(name = "autovehicul")
@XmlAccessorType(XmlAccessType.NONE)

public class Autovehicul implements Comparable<Autovehicul>, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_autovehicul")
    protected Integer idAutovehicul;
    private String model;
    @Column(name = "nr_imatriculare")
    private String nrImatriculare;
    @Column(name = "serie_sasiu")
    private String serieSasiu;
    @Column(name = "an_fabricatie")
    private String anFabricatie;
    @Column(name = "capacitate_cilindrica")
    private String capacitateCilindrica;
    @Enumerated(EnumType.STRING)
    protected Combustibil combustibil;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inmatriculare")
    private Date dataInmatriculare;
    private String culoare;
    private String descriere;
    @Column(name = "nr_pasageri")
    private Integer nrPasageri;
    private String status;
    @Column(name = "cost_inchiriere")
    private Double costInchiriere;

    @OneToMany(mappedBy = "autovehicul",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Inchiriere> inchirieri = new ArrayList<>();

    public Autovehicul() {
        super();
    }

    public Autovehicul(Integer idAutovehicul, String model, String nrImatriculare, String serieSasiu, String anFabricatie, String capacitateCilindrica, Combustibil combustibil, Date dataInmatriculare, String culoare, String descriere, Integer nrPasageri, String status, Double costInchiriere) {
        this.idAutovehicul = idAutovehicul;
        this.model = model;
        this.nrImatriculare = nrImatriculare;
        this.serieSasiu = serieSasiu;
        this.anFabricatie = anFabricatie;
        this.capacitateCilindrica = capacitateCilindrica;
        this.combustibil = combustibil;
        this.dataInmatriculare = dataInmatriculare;
        this.culoare = culoare;
        this.descriere = descriere;
        this.nrPasageri = nrPasageri;
        this.status = status;
        this.costInchiriere = costInchiriere;
    }

    public Autovehicul(Integer idAutovehicul, String model, String nrImatriculare, String serieSasiu, String anFabricatie, String capacitateCilindrica, Combustibil combustibil, Date dataInmatriculare, String culoare, String descriere, Integer nrPasageri) {
        this.idAutovehicul = idAutovehicul;
        this.model = model;
        this.nrImatriculare = nrImatriculare;
        this.serieSasiu = serieSasiu;
        this.anFabricatie = anFabricatie;
        this.capacitateCilindrica = capacitateCilindrica;
        this.combustibil = combustibil;
        this.dataInmatriculare = dataInmatriculare;
        this.culoare = culoare;
        this.descriere = descriere;
        this.nrPasageri = nrPasageri;
        this.status = "Valabil in curand";
    }

    public Autovehicul(String model, String nrImatriculare, Double costInchiriere) {
        this.model = model;
        this.nrImatriculare = nrImatriculare;
        this.costInchiriere = costInchiriere;
    }

    public Integer getIdAutovehicul() {
        return idAutovehicul;
    }

    public void setIdAutovehicul(Integer idAutovehicul) {
        this.idAutovehicul = idAutovehicul;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNrImatriculare() {
        return nrImatriculare;
    }

    public void setNrImatriculare(String nrImatriculare) {
        this.nrImatriculare = nrImatriculare;
    }

    public String getSerieSasiu() {
        return serieSasiu;
    }

    public void setSerieSasiu(String serieSasiu) {
        this.serieSasiu = serieSasiu;
    }

    public String getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(String anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public String getCapacitateCilindrica() {
        return capacitateCilindrica;
    }

    public void setCapacitateCilindrica(String capacitateCilindrica) {
        this.capacitateCilindrica = capacitateCilindrica;
    }

    public Combustibil getCombustibil() {
        return combustibil;
    }

    public void setCombustibil(Combustibil combustibil) {
        this.combustibil = combustibil;
    }

    public Date getDataInmatriculare() {
        return dataInmatriculare;
    }

    public void setDataInmatriculare(Date dataInmatriculare) {
        this.dataInmatriculare = dataInmatriculare;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Integer getNrPasageri() {
        return nrPasageri;
    }

    public void setNrPasageri(Integer nrPasageri) {
        this.nrPasageri = nrPasageri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCostInchiriere() {
        return costInchiriere;
    }

    public void setCostInchiriere(Double costInchiriere) {
        this.costInchiriere = costInchiriere;
    }

    public List<Inchiriere> getInchirieri() {
        return inchirieri;
    }

    public void setInchirieri(List<Inchiriere> inchirieri) {
        this.inchirieri = inchirieri;
    }

    @Override
    public String toString() {
        return "\n\t\tAutovehicul [idAutovehicul=" + idAutovehicul + ", model=" + model
                + ", anFabricatie=" + anFabricatie + ", combustibil=" + combustibil + ", culoare=" + culoare + ", status=" + status + ", costInchiriere=" + costInchiriere + "]";
    }

    @Override
    public int compareTo(Autovehicul o) {
        if (this.equals(o))
            return 0;
        return this.getCostInchiriere().compareTo(o.getCostInchiriere());
    }
}
