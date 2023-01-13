package org.scrum.domain.rent;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@XmlRootElement(name = "rent")
@JsonRootName("rent")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Transactional
public class Inchiriere implements Serializable {
    @Min(1)
    @NotNull(message = "IdInchiriere is required!")
    @Id
    @GeneratedValue
    @Column(name = "id_inchiriere")
    private Integer idInchiriere;
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
//    @JsonProperty("client")
//    @RestResource
//    @JsonManagedReference
    @XmlElement(name="client")
    @JoinColumn(name="client_id_client")
    protected Client client;
    @ManyToOne
    @JoinColumn(name="autovehicul_id_autovehicul")
    protected Autovehicul autovehicul;

    @NotNull(message = "DataInchiriere is required!")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_inchiriere")
    private Date dataInchiriere;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_returnare")
    private Date dataReturnare;

    //    @NotNull(message = "ZileInchiriere is required!")
//    @Min(value = 1, message = "ZileInchiriere must be equal or bigger than 1!")
    @Column(name = "zile_inchiriere")
    private Integer zileInchiriere;
    @Column(name = "cost_inchiriere")
    private Double costInchiriere = 0.0;
    private String status;

    public Inchiriere() {
        super();
    }

    public Inchiriere(Integer idInchiriere, Client client, Autovehicul autovehicul, Date dataInchiriere, Integer zileInchiriere) {
        this.idInchiriere = idInchiriere;
        this.client = client;
        this.autovehicul = autovehicul;
        this.dataInchiriere = dataInchiriere;
        this.zileInchiriere = zileInchiriere;
        this.dataReturnare = calculDataReturnare(dataInchiriere, zileInchiriere);
        this.costInchiriere = calculCostInchiriere(autovehicul, zileInchiriere);
    }

    public Inchiriere(Integer idInchiriere, Client client, Autovehicul autovehicul, Date dataInchiriere) {
        this.idInchiriere = idInchiriere;
        this.client = client;
        this.dataInchiriere = dataInchiriere;
        this.autovehicul = autovehicul;
    }

    public Inchiriere(Date dataInchiriere) {
        this.dataInchiriere = dataInchiriere;
    }

    private Date calculDataReturnare(Date data, Integer zile) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DATE, zile);
        return c.getTime();
    }

    private Double calculCostInchiriere(Autovehicul a, Integer zile) {
        return a.getCostInchiriere() * zile;
    }

    private Double calculCostInchiriere(Date dataStart, Date dataRetur, Double pret) {
        long difference_In_Time = dataRetur.getTime() - dataStart.getTime();
        long nrzile = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        return pret * nrzile;
    }

    private Integer calculZileInchiriere(Date dataStart, Date dataRetur) {
        long difference_In_Time = dataRetur.getTime() - dataStart.getTime();
        long nrzile = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        return (int) nrzile;
    }

    public Integer getIdInchiriere() {
        return idInchiriere;
    }

    public void setIdInchiriere(Integer idInchiriere) {
        this.idInchiriere = idInchiriere;
    }

//    @JsonProperty("client")
    @JsonGetter("customer")
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
        this.costInchiriere = calculCostInchiriere(this.dataInchiriere, this.dataReturnare, autovehicul.getCostInchiriere());
    }

    public Date getDataInchiriere() {
        return dataInchiriere;
    }

    public void setDataInchiriere(Date dataInchiriere) {
        this.dataInchiriere = dataInchiriere;
    }

    public Integer getZileInchiriere() {
        return zileInchiriere;
    }

    public void setZileInchiriere(Integer zileInchiriere) {
        this.zileInchiriere = zileInchiriere;
    }

    public Date getDataReturnare() {
        return dataReturnare;
    }

    public Double getCostInchiriere() {
        return costInchiriere;
    }

    public void adaugareClient(String numeClient, String numar_telefon, String email) {
        this.client = new Client(numeClient, numar_telefon, email);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataReturnare(Date dataReturnare) {
        this.dataReturnare = dataReturnare;
        this.zileInchiriere = calculZileInchiriere(this.dataInchiriere, dataReturnare);
    }

    public void modificareDataReturnare(Date dataReturnare) {
        this.dataReturnare = dataReturnare;
        this.costInchiriere = calculCostInchiriere(this.dataInchiriere, dataReturnare, this.autovehicul.getCostInchiriere());
        this.zileInchiriere = calculZileInchiriere(this.dataInchiriere, dataReturnare);
    }

    @Override
    public String toString() {
        return "\n\t\tInchiriere [idInchiriere=" + idInchiriere + ", client=" + (client != null ? client.getNume() : "-") + ", autovehicul=" + (autovehicul != null ? autovehicul.getModel() : "-") + ", dataInchiriere=" + dataInchiriere + ", dataReturnare=" + dataReturnare + ", costInchiriere=" + costInchiriere + "]";

    }
}
