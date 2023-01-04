package org.scrum.domain.rent;

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
@JsonIgnoreProperties({"CNP"}) //not to show sensitive data
@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.NONE)
public class Client implements Comparable<Client>, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_client")
    protected Integer idClient;
    private String nume;
    @Column(name = "numar_telefon")
    private String numarTelefon;
    private String email;
    private String adresa;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasterii")
    private Date dataNasterii;
    @Column(name = "cnp")
    private String CNP;
    private Double sold = 0.0;
    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Inchiriere> inchirieri = new ArrayList<>();

    public Client() {
        super();
    }

    public Client(Integer idClient, String nume, String numarTelefon, String email, String adresa, Date dataNasterii, String CNP) {
        super();
        this.idClient = idClient;
        this.nume = nume;
        this.numarTelefon = numarTelefon;
        this.email = email;
        this.adresa = adresa;
        this.dataNasterii = dataNasterii;
        this.CNP = CNP;
    }

    public Client(String nume, String numarTelefon, String email) {
        super();
        this.nume = nume;
        this.numarTelefon = numarTelefon;
        this.email = email;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public List<Inchiriere> getInchirieri() {
        return inchirieri;
    }

    public void setInchirieri(List<Inchiriere> inchirieri) {
        this.inchirieri = inchirieri;
    }

    @Override
    public int compareTo(Client o) {
        if (this.equals(o))
            return 0;
        return this.getNume().compareTo(o.getNume());
    }

    @Override
    public String toString() {
        return "\n\t\tClient [idClient=" + idClient + ", nume=" + nume
                + ", numarTelefon=" + numarTelefon + ", email=" + email + ", sold=" + sold + "]";
    }
}
